package org.ehrbase.fhirbridge.fhir.bundle;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractBundleValidator implements FhirTransactionValidator {

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        Bundle bundle = (Bundle) payload;

        if (bundle.getType() != Bundle.BundleType.TRANSACTION) {
            throw new UnprocessableEntityException(); // TODO
        }

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            Bundle.BundleEntryRequestComponent request = entry.getRequest();
            if (request.getMethod() != Bundle.HTTPVerb.POST) {
                throw new UnprocessableEntityException(); // TODO
            }
            Resource resource = entry.getResource();
            if (!Objects.equals(request.getUrl(), resource.getResourceType().name())) {
                throw new UnprocessableEntityException(); // TODO
            }
        }
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> parameters) {
        throw new UnsupportedOperationException("Response validation is not supported");
    }
}
