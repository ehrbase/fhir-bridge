package org.ehrbase.fhirbridge.fhir.util;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.PrimitiveType;
import org.hl7.fhir.r4.model.Resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class FhirUtils {

    private FhirUtils() {
    }

    public static Profile getBundleProfile(Bundle bundle) {
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (FhirUtils.hasProfile(entry.getResource(), Profile.BLOOD_GAS_PANEL)) {
                return Profile.BLOOD_GAS_PANEL;
            } else if (FhirUtils.hasProfile(entry.getResource(), Profile.DIAGNOSTIC_REPORT_LAB)) {
                return Profile.DIAGNOSTIC_REPORT_LAB;
            }
        }

        return null;
    }

    public static boolean hasProfile(Resource resource, Profile profile) {
        return hasAnyProfile(resource, profile);
    }

    public static boolean hasAnyProfile(Resource resource, Profile... profiles) {
        Set<String> c1 = resource.getMeta().getProfile()
                .stream()
                .map(PrimitiveType::getValue)
                .collect(Collectors.toSet());
        Set<String> c2 = Arrays.stream(profiles)
                .map(Profile::getUri)
                .collect(Collectors.toSet());
        return !Collections.disjoint(c1, c2);
    }
}
