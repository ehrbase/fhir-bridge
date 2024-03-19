package org.ehrbase.fhirbridge.fhir.encounter.validator;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Encounter;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;

public class KDSEncounterValidator implements FhirTransactionValidator {
    @Override
    public void validateRequest(Object payload, Map<String, Object> map) {
        Encounter encounter = (Encounter) payload;
        validate(encounter);
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> map) {
        Encounter encounter = (Encounter) payload;
        validate(encounter);
    }

    private void validate(Encounter encounter){
        if(!encounter.hasServiceType()){
            throw new UnprocessableEntityException("Encounter serviceType missing!");
        }
        if(!encounter.hasIdentifier()){
            throw new UnprocessableEntityException("Encounter identifier missing!");
        }
        if(encounter.hasStatus() && !encounter.getStatus().equals(Encounter.EncounterStatus.FINISHED)){
            throw new UnprocessableEntityException("Encounter status has to be finished !");
        }
    }
}
