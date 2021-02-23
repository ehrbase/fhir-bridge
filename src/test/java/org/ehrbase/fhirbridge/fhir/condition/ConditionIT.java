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
