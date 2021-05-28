package org.ehrbase.fhirbridge.fhir.patient;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Integration Tests that validate "Provide Patient" transaction.
 */
class ProvidePatientTransactionIT extends AbstractTransactionIT {

    @Test
    void providePatientCreate() throws Exception {
        var outcome = create("Patient/transactions/provide-patient-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void providePatientConditionalUpdate() throws Exception {
        MethodOutcome outcome;

        outcome = create("Patient/transactions/provide-patient-create.json");
        var id = outcome.getId();

        outcome = update("Patient/transactions/provide-patient-update.json", "Patient?_id=" + id.getIdPart() + "&identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        var patient = (Patient) outcome.getResource();

        Assertions.assertEquals(PATIENT_ID, patient.getIdentifier().get(0).getValue());
    }
}
