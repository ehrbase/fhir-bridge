package org.ehrbase.fhirbridge.fhir.support;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Bundle;

import java.util.Optional;

public class Bundles {

    private Bundles() {
    }

    public static Optional<Profile> getTransactionProfile(Bundle bundle) {
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (Resources.hasProfile(entry.getResource(), Profile.BLOOD_GAS_PANEL)) {
                return Optional.of(Profile.BLOOD_GAS_PANEL);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.ANTI_BODY_PANEL)) {
                return Optional.of(Profile.ANTI_BODY_PANEL);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.DIAGNOSTIC_REPORT_LAB)) {
                return Optional.of(Profile.DIAGNOSTIC_REPORT_LAB);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.VIROLOGISCHER_BEFUND)) {
                return Optional.of(Profile.VIROLOGISCHER_BEFUND);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.UCC_SENSORDATEN_STEPS)) {
                return Optional.of(Profile.UCC_SENSORDATEN_STEPS);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.UCC_SENSORDATEN_VITALSIGNS)) {
                return Optional.of(Profile.UCC_SENSORDATEN_VITALSIGNS);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.UCC_SENSORDATEN_VITALSIGNS)) {
                return Optional.of(Profile.UCC_SENSORDATEN_VITALSIGNS);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.ITI65)) {
                return Optional.of(Profile.ITI65);
            }
            if (Resources.hasProfile(entry.getResource(), Profile.UCC_APP_PRO_DATEN)) {
                return Optional.of(Profile.UCC_APP_PRO_DATEN);
            }
        }
        return Optional.empty();
    }
}
