package org.ehrbase.fhirbridge.fhir.encounter.validator;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Encounter;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;

public class KDSEncounterValidator implements FhirTransactionValidator {
    @Override
    public void validateRequest(Object payload, Map<String, Object> map) {
        Encounter encounter = (Encounter) payload;
        if(encounter.hasStatus() && !encounter.getStatus().equals(Encounter.EncounterStatus.FINISHED)){
            throw new InternalErrorException("Encounter status has to be finished !");
        }
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> map) {
        Encounter encounter = (Encounter) payload;
        if(encounter.hasStatus() && !encounter.getStatus().equals(Encounter.EncounterStatus.FINISHED)){
            throw new InternalErrorException("Encounter status has to be finished !");
        }
    }
}
