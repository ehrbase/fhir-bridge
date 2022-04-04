package org.ehrbase.fhirbridge.fhir.patient;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.UUID;

/**
 * Integration Tests that validate "Find Patient" transaction.
 */
class FindPatientTransactionIT extends AbstractTransactionIT {

    private String patientId;

    @BeforeEach
    public void setup() {
        patientId = UUID.randomUUID().toString();
    }

    @Test
    void findPatientRead() throws IOException {
        var outcome = create("Patient/transactions/provide-patient-create.json");
        var id = outcome.getId();

        var patient = read(id.getIdPart(), Patient.class);

        Assertions.assertNotNull(patient);
        Assertions.assertNotNull(patient.getId(), id.getIdPart());
        Assertions.assertEquals(patientId, patient.getIdentifier().get(0).getValue());
    }

    @Test
    void findPatientVRead() throws IOException {
        var outcome = create("Patient/transactions/provide-patient-create.json");
        var id = outcome.getId();

        var patient = vread(id.getIdPart(), id.getVersionIdPart(), Patient.class);
        Assertions.assertNotNull(patient);
        Assertions.assertNotNull(patient.getId(), id.getIdPart());
        Assertions.assertNotNull(patient.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(patientId, patient.getIdentifier().get(0).getValue());
    }

    @Test
    void findPatientSearch() throws IOException {
        String patientId = null;
        for (int i = 0; i < 3; i++) {
            String uuid = UUID.randomUUID().toString();
            if (i == 0) {
                patientId = uuid;
            }
            create("Patient/transactions/find-patient-search.json", uuid);
        }

        Bundle bundle = search("Patient?identifier=" + patientId + "&birthdate=2000-09-30");

        Assertions.assertEquals(1, bundle.getTotal());
    }

    protected MethodOutcome create(String resourceLocation, String patientId) throws IOException {
        return client.create()
                .resource(getResourceAsString(resourceLocation, patientId))
                .execute();
    }

    protected String getResourceAsString(String resourceLocation, String patientId) throws IOException {
        Reader reader = new InputStreamReader(new ClassPathResource(resourceLocation).getInputStream(), StandardCharsets.UTF_8);
        String resource = FileCopyUtils.copyToString(reader);
        return resource.replaceAll(PATIENT_ID_TOKEN, patientId);
    }

    protected String getResourceAsString(String resourceLocation) throws IOException {
        Reader reader = new InputStreamReader(new ClassPathResource(resourceLocation).getInputStream(), StandardCharsets.UTF_8);
        String resource = FileCopyUtils.copyToString(reader);
        return resource.replaceAll(PATIENT_ID_TOKEN, patientId);
    }
}
