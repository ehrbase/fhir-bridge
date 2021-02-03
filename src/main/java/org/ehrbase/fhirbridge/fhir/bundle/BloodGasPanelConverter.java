package org.ehrbase.fhirbridge.fhir.bundle;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BloodGasPanelConverter extends AbstractBundleConverter<Observation> {

    @Override
    public Observation convert(@NonNull Bundle bundle) {
        Observation observation = extractRoot(bundle, Profile.BLOOD_GAS_PANEL);

        return observation;
    }
}
