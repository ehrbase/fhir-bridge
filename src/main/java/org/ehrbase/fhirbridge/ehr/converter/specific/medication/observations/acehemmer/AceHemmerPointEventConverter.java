package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.acehemmer;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ArzneimittelNameDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;

public class AceHemmerPointEventConverter extends GeccoMedikationPointEventConverter<AceHemmerBeliebigesEreignisPointEvent> {
    @Override
    protected AceHemmerBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AceHemmerBeliebigesEreignisPointEvent aceHemmerBeliebigesEreignisPointEvent = new AceHemmerBeliebigesEreignisPointEvent();
        aceHemmerBeliebigesEreignisPointEvent.setArzneimittelNameDefiningCode(getArzneimittelName(resource));
        return aceHemmerBeliebigesEreignisPointEvent;
    }

    private ArzneimittelNameDefiningCode2 getArzneimittelName(MedicationStatement resource) {
        for (Coding coding:resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.DIMDI_ATC.getUrl())) {
                return mapArzneimittelName(coding);
            }
        }
        throw new UnprocessableEntityException("The MedicationStatement is missing the medication");
    }

    private ArzneimittelNameDefiningCode2 mapArzneimittelName(Coding coding) {
        Map<String, ArzneimittelNameDefiningCode2> arzneimittelNameDefiningCodeMap = ArzneimittelNameDefiningCode2.getCodesAsMap();
        if(arzneimittelNameDefiningCodeMap.containsKey(coding.getCode())){
            return arzneimittelNameDefiningCodeMap.get(coding.getCode());
        }
        throw new UnprocessableEntityException("Invalid Arzneimittel code  " + coding.getCode());
    }
}
