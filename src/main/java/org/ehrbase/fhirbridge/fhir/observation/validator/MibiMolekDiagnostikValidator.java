package org.ehrbase.fhirbridge.fhir.observation.validator;

import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;

public class MibiMolekDiagnostikValidator implements FhirTransactionValidator {
    @Override
    public void validateRequest(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    private void validate(Object payload, Map<String, Object> map) {
        // Check 1..1
        // Check Fixed Codings
    }


}
