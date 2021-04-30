package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.covid19therapie;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ArzneimittelNameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;

public class Covid19TherapiePointEventConverter extends GeccoMedikationPointEventConverter<Covid19TherapieBeliebigesEreignisPointEvent> {
    @Override
    protected Covid19TherapieBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        Covid19TherapieBeliebigesEreignisPointEvent covid19TherapieBeliebigesEreignisPointEvent = new Covid19TherapieBeliebigesEreignisPointEvent();
        covid19TherapieBeliebigesEreignisPointEvent.setArzneimittelNameDefiningCode(getArzneimittelName(resource));
        return covid19TherapieBeliebigesEreignisPointEvent;
    }

    private ArzneimittelNameDefiningCode getArzneimittelName(MedicationStatement resource) {
        for (Coding coding:resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.DIMDI_ATC.getUrl())) {
                return mapArzneimittelName(coding);
            }
        }
        throw new UnprocessableEntityException("The MedicationStatement is missing the medication");
    }

    private ArzneimittelNameDefiningCode mapArzneimittelName(Coding coding) {
       Map<String, ArzneimittelNameDefiningCode> arzneimittelNameDefiningCodeMap = ArzneimittelNameDefiningCode.getCodesAsMap();
       if(arzneimittelNameDefiningCodeMap.containsKey(coding.getCode())){
           return arzneimittelNameDefiningCodeMap.get(coding.getCode());
       }
        throw new UnprocessableEntityException("Invalid Arzneimittel code " + coding.getCode());
    }

}
