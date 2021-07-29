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
            } else{
                return returnProfiles (entry);
            }
        }
        return Optional.empty();
    }

    public static Optional<Profile> returnProfiles(Bundle.BundleEntryComponent entry){
        if (Resources.hasProfile(entry.getResource(), Profile.ANTI_BODY_PANEL)) {
            return Optional.of(Profile.ANTI_BODY_PANEL);
        }
        if (Resources.hasProfile(entry.getResource(), Profile.DIAGNOSTIC_REPORT_LAB)) {
            return Optional.of(Profile.DIAGNOSTIC_REPORT_LAB);
        }
        if (Resources.hasProfile(entry.getResource(), Profile.VIROLOGISCHER_BEFUND)) {
            return Optional.of(Profile.VIROLOGISCHER_BEFUND);
        }
        return Optional.empty();
    }
}
