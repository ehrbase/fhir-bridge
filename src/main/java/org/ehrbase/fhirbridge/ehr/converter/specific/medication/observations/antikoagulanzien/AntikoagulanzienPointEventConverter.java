package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.antikoagulanzien;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedicationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ArzneimittelNameDefiningCode4;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;

public class AntikoagulanzienPointEventConverter extends GeccoMedicationPointEventConverter<AntikoagulanzienBeliebigesEreignisPointEvent> {
    @Override
    protected AntikoagulanzienBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AntikoagulanzienBeliebigesEreignisPointEvent antikoagulanzienBeliebigesEreignisPointEvent = new AntikoagulanzienBeliebigesEreignisPointEvent();
        antikoagulanzienBeliebigesEreignisPointEvent.setArzneimittelNameDefiningCode(getArzneimittelName(resource));
        return antikoagulanzienBeliebigesEreignisPointEvent;
    }

    private ArzneimittelNameDefiningCode4 getArzneimittelName(MedicationStatement resource) {
        for (Coding coding:resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.DIMDI_ATC.getUrl())) {
                return mapArzneimittelName(coding);
            }
        }
        throw new UnprocessableEntityException("The MedicationStatement is missing the medication");
    }

    private ArzneimittelNameDefiningCode4 mapArzneimittelName(Coding coding) {
        Map<String, ArzneimittelNameDefiningCode4> arzneimittelNameDefiningCodeMap = ArzneimittelNameDefiningCode4.getCodesAsMap();
        if(arzneimittelNameDefiningCodeMap.containsKey(coding.getCode())){
            return arzneimittelNameDefiningCodeMap.get(coding.getCode());
        }
        throw new UnprocessableEntityException("Invalid Arzneimittel code  " + coding.getCode());
    }
}
