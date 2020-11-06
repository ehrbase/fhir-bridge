package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class DiagnosticReportIT {

    private final FhirContext context = FhirContext.forR4();

    private final IGenericClient client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge-poc/fhir/");

    @Test
    void create() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("DiagnosticReport/create-with-observation.json").getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create()
                .resource(resource)
                .execute();

        Assertions.assertNotNull(outcome.getId());
        Assertions.assertEquals(true, outcome.getCreated());
    }

    @Test
    void createWithMissingObservation() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("DiagnosticReport/create-with-missing-observation.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create()
                .resource(resource);
        Exception exception = Assertions.assertThrows(InvalidRequestException.class, createTyped::execute);

        Assertions.assertEquals("HTTP 400 : Resource Observation/f001 not found, specified in path: DiagnosticReport.result", exception.getMessage());
    }
}
