package org.ehrbase.fhirbridge.fhir.bundle.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;

import java.util.Map;


public class UCCSensorDatenValidator extends AbstractBundleValidator {

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        super.validateRequest(payload, parameters);
    }
}