package org.ehrbase.fhirbridge.fhir.patient.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.camel.processor.FhirProfileValidator;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;

public class KdsPersonValidator implements FhirTransactionValidator {
    @Override
    public void validateRequest(Object payload, Map<String, Object> map) {
        Patient patient = (Patient) payload;
        validate(patient, map);
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> map) {
        Patient patient = (Patient) payload;
        validate(patient, map);
    }

    private void validate(Patient patient, Map<String, Object> map) {
        if(!patient.hasBirthDate()){
            throw new UnprocessableEntityException("KDS Person must include a BirthDate!");
        }
        if(!patient.hasGender()){
            throw new UnprocessableEntityException("KDS Person must include a gender");
        }
    }


}
