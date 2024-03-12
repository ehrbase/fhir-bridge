package org.ehrbase.fhirbridge.fhir.patient;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdspatient.KDSPatientCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.KDSPersonComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.patient.validator.KdsPersonValidator;
import org.hl7.fhir.r4.model.Patient;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Period;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for {@link Patient Patient} resource.
 */
class KDSPatientIT extends AbstractMappingTestSetupIT {

    public KDSPatientIT() {
        super("Patient/kdspatient/", Patient.class);
    }

    @Test
    void createPatient() throws IOException {
        create("create-kds-patient.json");
    }

    @Test
    void mappingPatient() throws IOException {
        Patient patient = (Patient) super.testFileLoader.loadResource("create-kds-patient.json");
        KDSPatientCompositionConverter kdsPatientCompositionConverter = new KDSPatientCompositionConverter();
        KDSPersonComposition mappedKDSPerson = kdsPatientCompositionConverter.convert(patient);
        Diff diff = compareCompositions(getJavers(), "paragon-create-kds-patient.json", mappedKDSPerson);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void createWithoutGender() throws IOException {
        Exception exception = executeValidatorException("create-without-gender-kds-patient.json");
        assertEquals("KDS Person must include a gender", exception.getMessage());
    }

    @Test
    void createWithoutBirthDate() throws IOException {
        Exception exception = executeValidatorException("create-without-birthdate-kds-patient.json");
        assertEquals("KDS Person must include a BirthDate!", exception.getMessage());
    }


    @Override
    public Exception executeMappingException(String path) throws IOException {
        Patient patient = (Patient) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
            new KDSPatientCompositionConverter().convert((patient))
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {

    }

    public Exception executeValidatorException(String path) throws IOException {
        Patient patient = (Patient) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new KdsPersonValidator().validateRequest(patient, null);
        });
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KDSPersonComposition.class, List.of("location", "feederAudit", "startTimeValue")))
                .registerValueObject((GeschlechtEvaluation.class))
                .registerValueObject((PersonendatenAdminEntry.class))
                .registerValueObject((VitalstatusEvaluation.class))
                .registerValueObject((PersonCluster.class))
                .registerValueObject((DatenZurGeburtCluster.class))
                .registerValueObject((AngabenZumTodCluster.class))
                .registerValueObject((KontaktpersonCluster.class))
                .registerValueObject((Period.class))
                .registerValueObject((NameCluster.class))
                .registerValueObject((GeburtsnameCluster.class))
                .registerValueObject((PostfachCluster.class))
                .registerValueObject((VerwaltungsorganisationCluster.class))
                .build();
    }
}
