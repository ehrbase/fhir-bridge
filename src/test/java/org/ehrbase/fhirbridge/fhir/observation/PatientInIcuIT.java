package org.ehrbase.fhirbridge.fhir.observation;


import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientinicu.PatientInIcuCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.WirdWurdeDieAktivitaetDurchgefuehrtDefiningCode;
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

public class PatientInIcuIT extends AbstractMappingTestSetupIT {

    public PatientInIcuIT() {
        super("Observation/PatientInIcu/", Observation.class); //fhir-Resource
    }

    @Test
    void createPatientInIcu() throws IOException {
        create("create-patient-in-icu-No.json");
    }

    @Test
    void mappingPatientInIcuNo() throws IOException {
        testMapping("create-patient-in-icu-No.json",
                "paragon-create-patient-in-icu-No.json");
    }

    @Test
    void mappingPatientInIcuUnknown() throws IOException {
        testMapping("create-patient-in-icu-Unknown.json",
                "paragon-create-patient-in-icu-Unknown.json");
    }

    @Test
    void mappingPatientInIcuYes() throws IOException {
        testMapping("create-patient-in-icu-Yes.json",
                "paragon-create-patient-in-icu-Yes.json");
    }



    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(PatientAufICUComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(PatientAufDerIntensivstationObservation.class)
                .registerValueObject(WirdWurdeDieAktivitaetDurchgefuehrtDefiningCode.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new PatientInIcuCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        PatientInIcuCompositionConverter patientInIcuCompositionConverter = new PatientInIcuCompositionConverter();
        PatientAufICUComposition mapped = patientInIcuCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
