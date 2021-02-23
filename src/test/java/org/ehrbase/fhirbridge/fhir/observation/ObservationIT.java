package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Observation Observation} resource.
 */
class ObservationIT extends AbstractMappingTestSetupIT {

    public ObservationIT() {
        super("Observation/", Observation.class);
    }

    @Test
    void createBloodPressure() throws IOException {
        create("create-blood-pressure.json");
    }

    @Test
    void createBodyHeight() throws IOException {
        create("create-body-height.json");
    }

    @Test
    void createBodyTemp() throws IOException {
        create("create-body-temp.json");
    }

    @Test
    void createBodyWeight() throws IOException {
        create("create-body-weight.json");
    }

    @Test
    void createClinicalFrailtyScaleScore() throws IOException {
        create("create-clinical-frailty-scale-score.json");
    }

    @Test
    void createCoronavirusNachweisTest() throws IOException {
        create("create-coronavirus-nachweis-test.json");
    }

    @Test
    void createFiO2() throws IOException {
        create("create-fio2.json");
    }

    @Test
    void createHeartRate() throws IOException {
        create("create-heart-rate.json");
    }

    @Test
    void createPatientInIcu() throws IOException {
        create("create-patient-in-icu.json");
    }

    @Test
    void createObservationLab() throws IOException {
        create("create-observation-lab.json");
    }

    @Test
    void createPregnancyStatus() throws IOException {
        create("create-pregnancy-status.json");
    }

    @Test
    void createRespiratoryRate() throws IOException {
        create("create-respiratory-rate.json");
    }

    @Test
    void createSofaScore() throws IOException {
        create("create-sofa-score.json");
    }

    @Test
    void createSmokingStatus() throws IOException {
        create("create-smoking-status.json");
    }

    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-observation-with-default-profile.json");

        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertTrue(StringUtils.startsWith(exception.getMessage(), "HTTP 422 : Default profile is not supported for Observation. One of the following profiles is expected:"));
    }

    @Test
    void createWithInvalidQuantity() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-observation-with-invalid-quantity.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertTrue(StringUtils.startsWith(exception.getMessage(), "HTTP 422 : HTTP status '400 Bad Request' was returned by EHRbase while trying to save the composition. Details: Wrong Status code. "));
    }


    @Test
    void createWithInvalidQuantityDatatype() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-observation-with-invalid-quantity-datatype.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Error parsing JSON: the primitive value must be a number", exception.getMessage());
    }


    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () -> {
            // new YourConverter().toComposition(((YourResource) domainResource)));
        });
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                // .registerValueObject(new ValueObjectDefinition(YourComposition.class, List.of("location")))
                .build();
    }
}
