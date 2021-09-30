package org.ehrbase.fhirbridge.fhir.medicationstatement;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GECCOMedikationCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.GECCOMedikationComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for {@link MedicationStatement} using Pharmacological Therapy profile.
 */
class MedicationStatementIT extends AbstractMappingTestSetupIT {

    public MedicationStatementIT() {
        super("MedicationStatement/", MedicationStatement.class);
    }

    @Test
    void createPharmacologicalTherapy() throws IOException {
        create("create-pharmacological-therapy.json");
    }

    @Test
    void createPharmacologicalTherapyAceInhibitors() throws IOException {
        create("create-pharmacological-therapy-ace-inhibitors-active.json");
    }

    @Test
    void createPharmacologicalTherapyAnticoagulants() throws IOException {
        create("create-pharmacological-therapy-anticoagulants.json");
    }

    @Test
    void createPharmacologicalTherapyImmunoglobulins() throws IOException {
        create("create-pharmacological-therapy-immunoglobulins.json");
    }

    @Test
    void createMappingAceInhibActive() throws IOException {
        testMapping("create-pharmacological-therapy-ace-inhibitors-active.json", "paragon-ace-inhib-active.json");
    }

    @Test
    void createMappingAceInhibNotTaken() throws IOException {
        testMapping("create-pharmacological-therapy-ace-inhibitors-not-taken.json", "paragon-ace-inhib-not-taken.json");
    }

    @Test
    void createMappingAceInhibStopped() throws IOException {
        testMapping("create-pharmacological-therapy-ace-inhibitors-stopped.json", "paragon-ace-inhib-stopped.json");
    }

    @Test
    void createMappingAceInhibUnknown() throws IOException {
        testMapping("create-pharmacological-therapy-ace-inhibitors-unknown.json", "paragon-ace-inhib-unknown.json");
    }

    @Test
    void createMappingAnticoagulants() throws IOException {
        testMapping("create-pharmacological-therapy-anticoagulants.json", "paragon-anticoagulants.json");
    }

    @Test
    void createMappingImmunoglobulins() throws IOException {
        testMapping("create-pharmacological-therapy-immunoglobulins.json", "paragon-immnuoglobulins.json");
    }

    @Test
    void createMappingTherapy() throws IOException {
        testMapping("create-pharmacological-therapy.json", "paragon-therapy.json");
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        MedicationStatement medicationStatement = (MedicationStatement) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () ->
                new GECCOMedikationCompositionConverter().convert(medicationStatement));
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        MedicationStatement medicationStatement = (MedicationStatement) super.testFileLoader.loadResource(resourcePath);
        GECCOMedikationCompositionConverter geccoMedikationCompositionConverter = new GECCOMedikationCompositionConverter();
        GECCOMedikationComposition mapped = geccoMedikationCompositionConverter.convert(medicationStatement);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOMedikationComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(Covid19TherapieObservation.class)
                .registerValueObject(AceHemmerObservation.class)
                .registerValueObject(ImmunglobulineObservation.class)
                .registerValueObject(AntikoagulanzienObservation.class)
                .registerValueObject(StatusCluster.class)
                .build();
    }
}
