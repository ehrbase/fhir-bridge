package org.ehrbase.fhirbridge.fhir.bundle.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;

public class Iti65BundleValidator implements FhirTransactionValidator {

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        Bundle bundle = (Bundle) payload;
        validateDocumentReference(bundle);
    }

    private void validateDocumentReference(Bundle bundle) {
        int count = 0;
        for(Bundle.BundleEntryComponent entry :bundle.getEntry()){
            if(entry.getResource().getResourceType().equals(ResourceType.DocumentReference)){
                count ++;
            }
        }
        if(count>1){
            throw new UnprocessableEntityException("Only one DocumentReference per MHD Bundle is supported!");
        }
    }

    @Override
    public void validateResponse(Object o, Map<String, Object> map) {
       // throw new UnsupportedOperationException("Response validation is not supported");
    }
}
