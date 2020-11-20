package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ObservationIT extends AbstractSetupIT {

    private final FhirContext context = FhirContext.forR4();

    private final IGenericClient client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge-poc/fhir/");

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
    void createCoronavirusNachweisTest() throws IOException {
        create("Observation/create-coronavirusnachweistest.json");
    }

    @Test
    void createSmokingStatus() throws IOException {
        create("Observation/create-smoking-status.json");
    }

    void create(String path) throws IOException {
        String resource = IOUtils.toString(new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }
}
