package org.ehrbase.fhirbridge.fhir.bundle.validator;

import org.hl7.fhir.r4.model.Bundle;

import java.util.Map;

public class AntiBodyPanelBundleValidator extends AbstractBundleValidator{
    
    @Override
    public void validateRequest(Object bundle, Map<String, Object> parameters) {
        super.validateRequest(bundle, parameters);
        validateAntiBodyPanel((Bundle) bundle);
    }

    private void validateAntiBodyPanel(Bundle bundle) {
    }
}
