package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Condition Condition} resource.
 */
class ConditionIT extends AbstractSetupIT {

    @Test
    void createDefault() throws IOException {
        create("Condition/create-condition-default.json");
    }

    @Test
    void createWithInvalidSubject() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Condition/create-condition-with-invalid-subject.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource);
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Subject identifier is required", exception.getMessage());
    }

    @Test
    void createSymptomCovidAbsent() throws IOException {
        create("Condition/create-symptoms-covid-19-absent.json");
    }

    @Test
    void createSymptomCovidPresent() throws IOException {
        create("Condition/create-symptoms-covid-19-present.json");
    }

    @Test
    void createSymptomCovidUnknown() throws IOException {
        create("Condition/create-symptoms-covid-19-unknown.json");
    }

    @Test
    void createDiagnoseChronicLiverDisease() throws IOException {
        create("Condition/create-chronic-liver-disease.json");
        create("Condition/create-chronic-liver-disease-autoimmune.json");
        create("Condition/create-chronic-liver-disease-chronic-viral-hepatitis.json");
        create("Condition/create-chronic-liver-disease-cirrhosis-of-liver.json");
        create("Condition/create-chronic-liver-disease-steatosis-of-liver.json");
    }

    @Test
    void createDiagnoseChronicLungDisease() throws IOException {
        create("Condition/create-chronic-lung-disease.json");
        create("Condition/create-chronic-lung-disease-asthma.json");
        create("Condition/create-chronic-lung-disease-fibrosis-of-lung.json");
        create("Condition/create-chronic-lung-disease-obstructive-sleep-apnea.json");
        create("Condition/create-chronic-lung-disease-pulmonary-hypertension.json");
        create("Condition/create-chronic-lung-disease-sleep-apnea.json");
        create("Condition/create-chronic-lung-disease-with-alveolar-hypoventilation.json");
        create("Condition/create-chronic-obstructive-lung-disease.json");
    }

    @Test
    void createDiagnoseDiabetesMellitus() throws IOException {
        create("Condition/create-diabetes-mellitus.json");
        create("Condition/create-diabetes-mellitus-type-1.json");
        create("Condition/create-diabetes-mellitus-type-2.json");
        create("Condition/create-diabetes-mellitus-type-2-insulin-treated.json");
    }

    @Test
    void createDiagnoseMalignantNeoplasticDisease() throws IOException {
        create("Condition/create-malignant-neoplastic-disease-absent.json");
        create("Condition/create-malignant-neoplastic-disease-present-active.json");
        create("Condition/create-malignant-neoplastic-disease-present-remission.json");
        create("Condition/create-malignant-neoplastic-disease-unknown.json");
    }


    @Test
    void createDiagnoseRheumatologicalImmunologicalDiseases() throws IOException {
        create("Condition/create-rheumatological-immunological-diseases-rheumatism.json");
        create("Condition/create-rheumatological-immunological-diseases-rheumatoid-arthritis.json");
    }

    @Test
    void createDiagnoseHIV() throws IOException {
        create("Condition/example-human-immunodeficiency-virus-infection1.json");
        create("Condition/example-human-immunodeficiency-virus-infection2.json");
        create("Condition/example-human-immunodeficiency-virus-infection3.json");
    }

    @Test
    void createDiagnoseCardiovascularDiseases() throws IOException {
        create("Condition/example-cardiovascular-diseases.json");

    }

    @Test
    void createDiagnoseChronicKidneyDisease() throws IOException {
        create("Condition/example-chronic-kidney-diseases1.json");
        /*create("Condition/example-chronic-kidney-diseases2.json");
        create("Condition/example-chronic-kidney-diseases3.json");
        create("Condition/example-chronic-kidney-diseases4.json");
        create("Condition/example-chronic-kidney-diseases5.json");*/

    }

    @Test
    void createDiagnoseChronicNeurologicalMentalDiseases() throws IOException {
        create("Condition/example-chronic-neurological-mental-diseases.json");

    }

    @Test
    void createDiagnoseOrganRecipient() throws IOException {
        create("Condition/example-organ-recipient.json");
        create("Condition/example-organ-recipient2.json");
        create("Condition/example-organ-recipient3.json");
        create("Condition/example-organ-recipient-unknown.json");
    }

    @Test
    void createDiagnoseGastrointerstinalUlcers() throws IOException {
        create("Condition/example-gastrointestinal-ulcers.json");
        create("Condition/example-gastrointestinal-ulcers2.json");
        create("Condition/example-gastrointestinal-ulcers3.json");
    }

    @Test
    void searchBySubject() throws IOException {
        createDefault();

        Bundle bundle = client.search()
                .forResource(Condition.class)
                .where(Condition.SUBJECT.hasChainedProperty(Patient.IDENTIFIER.exactly().code(PATIENT_ID)))
                .returnBundle(Bundle.class).execute();

        assertTrue(bundle.getTotal() >= 1);
    }

    @Test
    void searchByInvalidSubject() throws IOException {
        createDefault();

        Bundle bundle = client.search()
                .forResource(Condition.class)
                .where(Condition.SUBJECT.hasChainedProperty(Patient.IDENTIFIER.exactly().code("123456789")))
                .returnBundle(Bundle.class).execute();

        assertEquals(0, bundle.getTotal());
    }

    @Test
    void searchBySubjectAndCode() throws IOException {
        createDefault();

        Bundle bundle = client.search()
                .forResource(Condition.class)
                .where(Condition.SUBJECT.hasChainedProperty(Patient.IDENTIFIER.exactly().code(PATIENT_ID)))
                .and(Condition.CODE.exactly().code("B97.2"))
                .returnBundle(Bundle.class).execute();

        assertTrue(bundle.getTotal() >= 1);
    }

    @Test
    void searchBySubjectAndInvalidCode() throws IOException {
        createDefault();

        Bundle bundle = client.search()
                .forResource(Condition.class)
                .where(Condition.SUBJECT.hasChainedProperty(Patient.IDENTIFIER.exactly().code(PATIENT_ID)))
                .and(Condition.CODE.exactly().code("AAA"))
                .returnBundle(Bundle.class).execute();

        assertEquals(0, bundle.getTotal());
    }

    @Test
    void searchBySubjectAndStartTime() throws IOException {
        createDefault();

        Bundle bundle = client.search()
                .forResource(Condition.class)
                .where(Condition.SUBJECT.hasChainedProperty(Patient.IDENTIFIER.exactly().code(PATIENT_ID)))
                .and(Condition.RECORDED_DATE.afterOrEquals().day("2012-05-23"))
                .and(Condition.RECORDED_DATE.before().day("2012-05-25"))
                .returnBundle(Bundle.class).execute();

        assertTrue(bundle.getTotal() >= 1);
    }

    @Test
    void searchBySubjectAndInvalidStartTime() throws IOException {
        createDefault();

        Bundle bundle = client.search()
                .forResource(Condition.class)
                .where(Condition.SUBJECT.hasChainedProperty(Patient.IDENTIFIER.exactly().code(PATIENT_ID)))
                .and(Condition.RECORDED_DATE.after().day(new Date()))
                .returnBundle(Bundle.class).execute();

        assertEquals(0, bundle.getTotal());
    }

    private void create(String path) throws IOException {
        String resource = IOUtils.toString(new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }
}
