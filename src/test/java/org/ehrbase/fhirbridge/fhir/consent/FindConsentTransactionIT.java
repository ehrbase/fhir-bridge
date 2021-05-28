package org.ehrbase.fhirbridge.fhir.consent;

import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Consent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Find Consent" transaction.
 */
class FindConsentTransactionIT extends AbstractTransactionIT {

    @Disabled("Converter not yet implemented")
    @Test
    void findConsentRead() throws Exception {
        var outcome = create("Consent/transactions/provide-consent-create.json");
        var id = outcome.getId();

        var consent = read(id.getIdPart(), Consent.class);

        Assertions.assertNotNull(consent);
        Assertions.assertNotNull(consent.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, consent.getPatient().getIdentifier().getValue());
    }

    @Disabled("Converter not yet implemented")
    @Test
    void findConsentVRead() throws Exception {
        var outcome = create("Consent/transactions/provide-consent-create.json");
        var id = outcome.getId();

        var consent = vread(id.getIdPart(), id.getVersionIdPart(), Consent.class);
        Assertions.assertNotNull(consent);
        Assertions.assertNotNull(consent.getId(), id.getIdPart());
        Assertions.assertNotNull(consent.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, consent.getPatient().getIdentifier().getValue());
    }

    @Disabled("Converter not yet implemented")
    @Test
    void findConsentSearch() throws IOException {
        create("Consent/transactions/find-consent-search.json");
        create("Consent/transactions/find-consent-search.json");
        create("Consent/transactions/find-consent-search.json");

        Bundle bundle = search("Consent?subject.identifier=" + PATIENT_ID + "&status=rejected");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            var consent = (Consent) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, consent.getPatient().getIdentifier().getValue());
            Assertions.assertEquals(Consent.ConsentState.REJECTED, consent.getStatus());
        });
    }
}
