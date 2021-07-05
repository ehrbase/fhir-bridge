package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.IUpdateTyped;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

/**
 * Integration Tests that validate "Provide Condition" transaction.
 */
class ProvideConditionTransactionIT extends AbstractTransactionIT {

    @Test
    void provideConditionCreate() throws IOException {
        var outcome = create("Condition/transactions/provide-condition-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void provideConditionConditionalUpdate() throws IOException {
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

    @Test
    void provideConditionConditionalUpdateCodex() throws IOException {
        String patientIdentifier = UUID.randomUUID().toString();

        MethodOutcome outcome;
        String resource;

        resource = getResourceAsString("Condition/transactions/codex-create-condition.json");
        outcome = client.create()
                .resource(resource.replace("{{patientIdentifier}}", patientIdentifier))
                .execute();

        var id = outcome.getId();

        resource = getResourceAsString("Condition/transactions/codex-update-condition.json");
        IUpdateTyped update = client.update()
                .resource(resource.replace("{{patientIdentifier}}", patientIdentifier));

        outcome = update.conditional()
                .where(Condition.SUBJECT
                        .hasChainedProperty(Patient.IDENTIFIER.exactly()
                                .systemAndIdentifier("http://www.netzwerk-universitaetsmedizin.de/sid/crr-pseudonym", patientIdentifier)))
                .execute();

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        var condition = (Condition) outcome.getResource();

        var clinicalStatus = condition.getClinicalStatus();
        Assertions.assertEquals("resolved", clinicalStatus.getCodingFirstRep().getCode());

        var verificationStatus = condition.getVerificationStatus();
        Assertions.assertEquals("refuted", verificationStatus.getCodingFirstRep().getCode());
    }
}
