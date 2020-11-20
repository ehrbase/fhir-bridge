package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
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

class ProcedureIT extends AbstractSetupIT {

    private final FhirContext context = FhirContext.forR4();

    private final IGenericClient client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge-poc/fhir/");

    @Test
    void create() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Procedure/create.json").getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }

    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Procedure/create-with-default-profile.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Default profile is not supported", exception.getMessage());
    }

    @Test
    void createWithNonExistingSubject() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Procedure/create-with-non-existing-subject.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : EhrId not found for subject '123456789'", exception.getMessage());
    }
}
