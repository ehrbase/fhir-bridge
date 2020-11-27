package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Observation Observation} resource.
 */
class ObservationIT extends AbstractSetupIT {

    @Test
    void createBloodPressure() throws IOException {
        create("Observation/create-blood-pressure.json");
    }

    @Test
    void createBodyHeight() throws IOException {
        create("Observation/create-body-height.json");
    }

    @Test
    void createBodyTemp() throws IOException {
        create("Observation/create-body-temp.json");
    }

    @Test
    void createBodyWeight() throws IOException {
        create("Observation/create-body-weight.json");
    }

    @Test
    void createClinicalFrailtyScaleScore() throws IOException {
        create("Observation/create-clinical-frailty-scale-score.json");
    }

    @Test
    void createCoronavirusNachweisTest() throws IOException {
        create("Observation/create-coronavirus-nachweis-test.json");
    }

    @Test
    void createFiO2() throws IOException {
        create("Observation/create-fio2.json");
    }

    @Test
    void createHeartRate() throws IOException {
        create("Observation/create-heart-rate.json");
    }

    @Test
    void createPatientInIcu() throws IOException {
        create("Observation/create-patient-in-icu.json");
    }

    @Test
    void createObservationLab() throws IOException {
        create("Observation/create-observation-lab.json");
    }

    @Test
    void createPregnancyStatus() throws IOException {
        create("Observation/create-pregnancy-status.json");
    }

    @Test
    void createRespiratoryRate() throws IOException {
        create("Observation/create-respiratory-rate.json");
    }

    @Test
    void createSofaScore() throws IOException {
        create("Observation/create-sofa-score.json");
    }

    @Test
    void createSmokingStatus() throws IOException {
        create("Observation/create-smoking-status.json");
    }

    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Observation/create-with-default-profile.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Default profile is not supported", exception.getMessage());
    }

    private void create(String path) throws IOException {
        String resource = IOUtils.toString(new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }
}
