package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
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
        create("create-patient-discharge-alive.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void mappingAlive() throws IOException {
        testMapping("create-patient-discharge-alive.json",
                "paragon-patient-discharge-alive.json");
    }

    @Test
    void mappingDead() throws IOException {
        testMapping("create-patient-discharge-dead.json",
                "paragon-patient-discharge-dead.json");
    }

    @Test
    void mappingHospital() throws IOException {
        testMapping("create-patient-discharge-hospital.json",
                "paragon-patient-discharge-hospital.json");
    }

    @Test
    void mappingPalliative() throws IOException {
        testMapping("create-patient-discharge-palliative.json",
                "paragon-patient-discharge-palliative.json");
    }

    @Test
    void mappingReferral() throws IOException {
        testMapping("create-patient-discharge-referral.json",
                "paragon-patient-discharge-referral.json");
    }

    @Test
    void mappingUnknown() throws IOException {
        testMapping("create-patient-discharge-unknown.json",
                "paragon-patient-discharge-unknown.json");
    }

    // #####################################################################################
    // check exceptions

    @Test
    void createInvalidSystem() throws IOException {
        // copy of alive, manipulated lines 57
        Exception exception = executeMappingException("create-patient-discharge-invalid-system.json");
        assertEquals("The system is not correct. It should be 'http://snomed.info/sct', but it was 'http://loinc.org'.", exception.getMessage());
    }

    @Test
    void createInvalidCode() throws IOException {
        // copy of alive, manipulated line 58
        Exception exception = executeMappingException("create-patient-discharge-invalid-code.json");
        assertEquals("Value code 999999999 is not supported", exception.getMessage());
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
        return assertThrows(ConversionException.class, () ->
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