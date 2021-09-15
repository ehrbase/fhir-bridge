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

    @Test
    void createPatientDischargeDead() throws IOException {
        testMapping("create-patient-discharge_dead.json",
                "paragon-patient-discharge_dead.json");
    }

    @Test
    void createPatientDischargeHospital() throws IOException {
        testMapping("create-patient-discharge_hospital.json",
                "paragon-patient-discharge_hospital.json");
    }

    @Test
    void createPatientDischargePalliative() throws IOException {
        testMapping("create-patient-discharge_palliative.json",
                "paragon-patient-discharge_palliative.json");
    }

    @Test
    void createPatientDischargeReferral() throws IOException {
        testMapping("create-patient-discharge_referral.json",
                "paragon-patient-discharge_referral.json");
    }

    @Test
    void createPatientDischargeUnknown() throws IOException {
        testMapping("create-patient-discharge_unknown.json",
                "paragon-patient-discharge_unknown.json");
    }

    // #####################################################################################
    // check exceptions

    @Test
    void createPatientDischargeMissingValueCodeableConcept() throws IOException {
        // copy of alive, changed value in line 58 from 371827001 to 999999999
        Exception exception = executeMappingException("create-patient-discharge_missing-value.json");
        assertEquals("ValueCodeableConcept missing but is required by the FHIR-Bridge.", exception.getMessage());
    }

    @Test
    void createPatientDischargeInvalidValueCodeableConcept() throws IOException {
        // copy of alive, changed value in line 58 from 371827001 to 999999999
        Exception exception = executeMappingException("create-patient-discharge_invalid-value.json");
        assertEquals("ValueCodeableConcept is invalid.", exception.getMessage());
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