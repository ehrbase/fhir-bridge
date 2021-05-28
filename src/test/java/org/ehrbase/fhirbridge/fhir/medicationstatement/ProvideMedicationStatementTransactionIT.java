package org.ehrbase.fhirbridge.fhir.medicationstatement;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Integration Tests that validate "Provide Medication Statement" transaction.
 */
class ProvideMedicationStatementTransactionIT extends AbstractTransactionIT {

    @Test
    void provideMedicationStatementCreate() throws Exception {
        var outcome = create("MedicationStatement/transactions/provide-medication-statement-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void provideMedicationStatementConditionalUpdate() throws Exception {
        MethodOutcome outcome;

        outcome = create("MedicationStatement/transactions/provide-medication-statement-create.json");
        var id = outcome.getId();

        outcome = update("MedicationStatement/transactions/provide-medication-statement-update.json", "MedicationStatement?_id=" + id.getIdPart() + "&subject.identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        var medicationStatement = (MedicationStatement) outcome.getResource();

        Assertions.assertEquals(PATIENT_ID, medicationStatement.getSubject().getIdentifier().getValue());
        Assertions.assertEquals(MedicationStatement.MedicationStatementStatus.ONHOLD, medicationStatement.getStatus());
    }
}
