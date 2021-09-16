package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge.PatientDischargeCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.GECCOEntlassungsdatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.EntlassungsartAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.EntlassungsdatenKategorieElement;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Observation;
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

class PatientDischargeIT extends AbstractMappingTestSetupIT {

    public PatientDischargeIT() {
        super("Observation/PatientDischarge/", Observation.class); //fhir-Resource
    }

    @Test
    void createPatientDischarge() throws IOException {
        create("create-patient-discharge_alive.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void createPatientDischargeAlive() throws IOException {
        testMapping("create-patient-discharge_alive.json",
                "paragon-patient-discharge_alive.json");
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOEntlassungsdatenComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(EntlassungsartAdminEntry.class)
                .registerValueObject(EntlassungsdatenKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new PatientDischargeCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        PatientDischargeCompositionConverter patientDischargeCompositionConverter = new PatientDischargeCompositionConverter();
        GECCOEntlassungsdatenComposition mapped = patientDischargeCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}