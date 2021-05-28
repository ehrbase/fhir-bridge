package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Integration Tests that validate "Provide Condition" transaction.
 */
class ProvideConditionTransactionIT extends AbstractTransactionIT {

    @Test
    void provideConditionCreate() throws Exception {
        var outcome = create("Condition/transactions/provide-condition-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void provideConditionConditionalUpdate() throws Exception {
        MethodOutcome outcome;

        outcome = create("Condition/transactions/provide-condition-create.json");
        var id = outcome.getId();

        outcome = update("Condition/transactions/provide-condition-update.json", "Condition?_id=" + id.getIdPart() + "&subject.identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        var condition = (Condition) outcome.getResource();

        var clinicalStatus = condition.getClinicalStatus();
        Assertions.assertEquals("inactive", clinicalStatus.getCodingFirstRep().getCode());

        var verificationStatus = condition.getVerificationStatus();
        Assertions.assertEquals("unconfirmed", verificationStatus.getCodingFirstRep().getCode());

        Assertions.assertEquals(PATIENT_ID, condition.getSubject().getIdentifier().getValue());
    }
}
