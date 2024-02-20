package org.ehrbase.fhirbridge.fhir.observation.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Observation;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;

public class MibiKulturValidator implements FhirTransactionValidator {
    @Override
    public void validateRequest(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    public void validate(Object payload, Map<String, Object> map){
        Observation observation = (Observation) payload;
        checkForIdentifier(observation);
        checkForEncounter(observation);
    }

    private void checkForIdentifier(Observation observation) {
        if(!observation.hasIdentifier() || observation.getIdentifier().size() == 0 || !observation.getIdentifier().get(0).hasValue()){
            throw new UnprocessableEntityException("Mibi Kultur must include an Identifier and value for BerichtId");
        }
    }

    private void checkForEncounter(Observation observation) {
        if(!observation.hasEncounter() || !observation.getEncounter().hasIdentifier()|| !observation.getEncounter().getIdentifier().hasValue()){
            throw new UnprocessableEntityException("Mibi Kultur must include a Encounter with Identifier and Value !");
        }
    }
}
