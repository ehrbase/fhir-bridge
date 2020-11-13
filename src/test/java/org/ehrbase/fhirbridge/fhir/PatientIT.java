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

class PatientIT {

    private final FhirContext context = FhirContext.forR4();

    private final IGenericClient client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge-poc/fhir/");

    @Test
    void create() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Patient/create.json").getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }

    @Test
    void createInvalid() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Patient/create-invalid.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource);
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age, Element 'Patient.extension[1].extension[dateTimeOfDocumentation]': minimum required = 1, but only found 0", exception.getMessage());
    }

    @Test
    void createDefaultProfile() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("Patient/create-default-profile.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource);
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Default profile is not supported", exception.getMessage());
    }
}
