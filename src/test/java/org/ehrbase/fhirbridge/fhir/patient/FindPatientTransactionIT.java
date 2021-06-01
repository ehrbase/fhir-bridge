package org.ehrbase.fhirbridge.fhir.patient;

import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Integration Tests that validate "Find Patient" transaction.
 */
class FindPatientTransactionIT extends AbstractTransactionIT {

    @Test
    void findPatientRead() throws IOException {
        var outcome = create("Patient/transactions/provide-patient-create.json");
        var id = outcome.getId();

        var patient = read(id.getIdPart(), Patient.class);

        Assertions.assertNotNull(patient);
        Assertions.assertNotNull(patient.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, patient.getIdentifier().get(0).getValue());
    }

    @Test
    void findPatientVRead() throws IOException {
        var outcome = create("Patient/transactions/provide-patient-create.json");
        var id = outcome.getId();

        var patient = vread(id.getIdPart(), id.getVersionIdPart(), Patient.class);
        Assertions.assertNotNull(patient);
        Assertions.assertNotNull(patient.getId(), id.getIdPart());
        Assertions.assertNotNull(patient.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, patient.getIdentifier().get(0).getValue());
    }

    @Test
    void findPatientSearch() throws IOException, ParseException {
        for (int i = 0; i < 3; i++) {
            create("Patient/transactions/find-patient-search.json");
        }

        Bundle bundle = search("Patient?identifier=" + PATIENT_ID + "&birthdate=2000-09-30");

        Assertions.assertEquals(3, bundle.getTotal());

        Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse("2000-09-30");

        bundle.getEntry().forEach(entry -> {
            var patient = (Patient) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, patient.getIdentifier().get(0).getValue());
            Assertions.assertEquals(birthdate, patient.getBirthDate());
        });
    }
}
