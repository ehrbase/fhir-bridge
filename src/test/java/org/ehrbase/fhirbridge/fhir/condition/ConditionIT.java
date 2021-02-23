package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.*;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Condition Condition} resource.
 */
class ConditionIT extends AbstractMappingTestSetupIT {

    public ConditionIT() {
        super("Condition/", Condition.class);
    }

    @Test
    void createDefault() throws IOException {
        create("create-condition-default.json");
    }

    @Test
    void createWithInvalidSubject() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-condition-with-invalid-subject.json");
        ICreateTyped createTyped = client.create().resource(resource);
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Subject identifier is required", exception.getMessage());
    }

    @Test
    void createSymptomCovidAbsent() throws IOException {
        create("create-symptoms-covid-19-absent.json");
    }

    @Test
    void createSymptomCovidPresent() throws IOException {
        create("create-symptoms-covid-19-present.json");
    }

    @Test
    void createSymptomCovidUnknown() throws IOException {
        create("create-symptoms-covid-19-unknown.json");
    }

    @Test
    void createDiagnoseChronicLiverDisease() throws IOException {
        create("create-chronic-liver-disease.json");
        create("create-chronic-liver-disease-autoimmune.json");
        create("create-chronic-liver-disease-chronic-viral-hepatitis.json");
        create("create-chronic-liver-disease-cirrhosis-of-liver.json");
        create("create-chronic-liver-disease-steatosis-of-liver.json");
    }

    @Test
    void createDiagnoseChronicLungDisease() throws IOException {
        create("create-chronic-lung-disease.json");
        create("create-chronic-lung-disease-asthma.json");
        create("create-chronic-lung-disease-fibrosis-of-lung.json");
        create("create-chronic-lung-disease-obstructive-sleep-apnea.json");
        create("create-chronic-lung-disease-pulmonary-hypertension.json");
        create("create-chronic-lung-disease-sleep-apnea.json");
        create("create-chronic-lung-disease-with-alveolar-hypoventilation.json");
        create("create-chronic-obstructive-lung-disease.json");
    }

    @Test
    void createDiagnoseDiabetesMellitus() throws IOException {
        create("create-diabetes-mellitus.json");
        create("create-diabetes-mellitus-type-1.json");
        create("create-diabetes-mellitus-type-2.json");
        create("create-diabetes-mellitus-type-2-insulin-treated.json");
    }

    @Test
    void createDiagnoseMalignantNeoplasticDisease() throws IOException {
        create("create-malignant-neoplastic-disease-absent.json");
        create("create-malignant-neoplastic-disease-present-active.json");
        create("create-malignant-neoplastic-disease-present-remission.json");
        create("create-malignant-neoplastic-disease-unknown.json");
    }


    @Test
    void createDiagnoseRheumatologicalImmunologicalDiseases() throws IOException {
        create("create-rheumatological-immunological-diseases-rheumatism.json");
        create("create-rheumatological-immunological-diseases-rheumatoid-arthritis.json");
    }

    @Test
    void createDiagnoseHIV() throws IOException {
        create("example-human-immunodeficiency-virus-infection1.json");
        create("example-human-immunodeficiency-virus-infection2.json");
        create("example-human-immunodeficiency-virus-infection3.json");
    }

    @Test
    void createDiagnoseCardiovascularDiseases() throws IOException {
        create("example-cardiovascular-diseases.json");

    }

    @Test
    void createDiagnoseChronicKidneyDisease() throws IOException {
        create("example-chronic-kidney-diseases2.json");
        create("example-chronic-kidney-diseases3.json");
        create("example-chronic-kidney-diseases4.json");
        create("example-chronic-kidney-diseases5.json");
    }

    @Test
    void createDiagnoseChronicNeurologicalMentalDiseases() throws IOException {
        create("example-chronic-neurological-mental-diseases.json");
    }

    @Test
    void createDiagnoseComplicationsCovid19() throws IOException {
        create("example-complications-covid19-0.json");
        create("example-complications-covid19-1.json");
        create("example-complications-covid19-2.json");
        create("example-complications-covid19-3.json");
        create("example-complications-covid19-4.json");
        create("example-complications-covid19-5.json");
        create("example-complications-covid19-6.json");
        create("example-complications-covid19-7.json");
        create("example-complications-covid19-8.json");
        create("example-complications-covid19-9.json");
    }

    @Test
    void createDiagnoseOrganRecipient() throws IOException {
        create("example-organ-recipient.json");
        create("example-organ-recipient2.json");
        create("example-organ-recipient3.json");
    }

    @Test
    void createDiagnoseGastrointerstinalUlcers() throws IOException {
        create("example-gastrointestinal-ulcers.json");
        create("example-gastrointestinal-ulcers2.json");
        create("example-gastrointestinal-ulcers3.json");
    }

    @Test
    void createDiagnoseDependenceOnVentilator() throws IOException {
        create("dependence-on-ventilator.json");
        create("dependence-on-ventilator-2.json");
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

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Condition condition = (Condition) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () -> {
            // new YourConverter().toComposition((condition)));
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
