package org.ehrbase.fhirbridge.ehr.converter.pulseoximetry;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import static org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOxymetryCode.CODING_1;

public class PulseOximetryCodeChecker {

    void checkIfPulseOximetry(Observation observation) {
        if(observation.getCode().getCoding().size()==1){

        }else if(observation.getCode().getCoding().size()==1){

        }else{
            throw new UnprocessableEntityException("Too many Codes")
        }
        for (Coding code : observation.getCode().getCoding()){
            if(CODING_1.getCode().get(0).get(0) == code.getSystem() && CODING_1.getCode().get(0).get(1) == code.getCode()){

            }else if()
        // was wenn mehr als ein code auftaucht ?
            //2708-6
            // was mit dem bloodgas panel soll ich das dann als einzel messung hier auch setzen ?
        }

    }


}
