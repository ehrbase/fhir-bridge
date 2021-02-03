package org.ehrbase.fhirbridge.fhir.bundle;

import org.hl7.fhir.r4.model.Bundle;

import java.util.Map;

public class BloodGasPanelBundleValidator extends AbstractBundleValidator {

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        super.validateRequest(payload, parameters);

        Bundle bundle = (Bundle) payload;
    }
}
