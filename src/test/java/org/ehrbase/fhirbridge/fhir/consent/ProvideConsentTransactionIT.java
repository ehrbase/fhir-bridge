package org.ehrbase.fhirbridge.fhir.consent;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Consent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Provide Consent" transaction.
 */
class ProvideConsentTransactionIT extends AbstractTransactionIT {

    @Disabled("Converter not yet implemented")
    @Test
    void provideConsentCreate() throws IOException {
        var outcome = create("Consent/transactions/provide-consent-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Disabled("Converter not yet implemented")
    @Test
    void provideConsentConditionalUpdate() throws IOException {
        MethodOutcome outcome;

        outcome = create("Consent/transactions/provide-consent-create.json");
        var id = outcome.getId();

        outcome = update("Consent/transactions/provide-consent-update.json", "Consent?_id=" + id.getIdPart() + "&patient.identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        var consent = (Consent) outcome.getResource();

        var scope = consent.getScope();
        Assertions.assertEquals("research", scope.getCodingFirstRep().getCode());

        Assertions.assertEquals(PATIENT_ID, consent.getPatient().getIdentifier().getValue());
    }
}
