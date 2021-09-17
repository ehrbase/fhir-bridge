package org.ehrbase.fhirbridge.fhir.consent;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Consent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * Integration Tests that validate "Provide Consent" transaction.
 */
class ProvideConsentTransactionIT extends AbstractTransactionIT {

    @Test
    void provideConsentCreate() throws IOException {
        MethodOutcome outcome = create("Consent/transactions/provide-consent-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void provideConsentConditionalUpdate() throws IOException {
        MethodOutcome outcome;

        outcome = create("Consent/transactions/provide-consent-create.json");
        IIdType id = outcome.getId();

        outcome = update("Consent/transactions/provide-consent-update.json", "Consent?_id=" + id.getIdPart() + "&patient.identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        Consent consent = (Consent) outcome.getResource();

        Assertions.assertEquals("https://www.aerzteblatt.de/archiv/65440/DNR-Anordnungen-Das-fehlende-Bindeglied-UPDATED", consent.getPolicyFirstRep().getUri());

        Assertions.assertEquals(PATIENT_ID, consent.getPatient().getIdentifier().getValue());
    }
}
