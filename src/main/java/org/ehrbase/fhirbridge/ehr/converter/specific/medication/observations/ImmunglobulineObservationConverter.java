package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ArzneimittelNameDefiningCode3;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;

public class ImmunglobulineObservationConverter extends GeccoMedikationObservationConverter<ImmunglobulineObservation> {
    @Override
    protected ImmunglobulineObservation convertInternal(MedicationStatement resource) {
        ImmunglobulineObservation immunglobulineObservation = new ImmunglobulineObservation();
        immunglobulineObservation.setArzneimittelNameDefiningCode(getArzneimittelName(resource));
        getGrundDefiningCode(resource).ifPresent(immunglobulineObservation::setGrundDefiningCode);
        return immunglobulineObservation;
    }

    private ArzneimittelNameDefiningCode3 getArzneimittelName(MedicationStatement resource) {
        for (Coding coding:resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.DIMDI_ATC.getUrl())) {
                return mapArzneimittelName(coding);
            }
        }
        throw new ConversionException("The MedicationStatement is missing the medication");
    }

    private ArzneimittelNameDefiningCode3 mapArzneimittelName(Coding coding) {
        Map<String, ArzneimittelNameDefiningCode3> arzneimittelNameDefiningCodeMap = ArzneimittelNameDefiningCode3.getCodesAsMap();
        if(arzneimittelNameDefiningCodeMap.containsKey(coding.getCode())){
            return arzneimittelNameDefiningCodeMap.get(coding.getCode());
        }
        throw new ConversionException("Invalid medicationCodeableConcept code " + coding.getCode());
    }
}
