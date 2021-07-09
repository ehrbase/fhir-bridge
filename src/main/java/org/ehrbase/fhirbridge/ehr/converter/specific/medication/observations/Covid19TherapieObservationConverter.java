package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ArzneimittelNameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;

public class Covid19TherapieObservationConverter extends GeccoMedikationObservationConverter<Covid19TherapieObservation> {

    @Override
    protected Covid19TherapieObservation convertInternal(MedicationStatement resource) {
        Covid19TherapieObservation covid19TherapieObservation = new Covid19TherapieObservation();
        covid19TherapieObservation.setArzneimittelNameDefiningCode(getArzneimittelName(resource));
        return covid19TherapieObservation;
    }

    private ArzneimittelNameDefiningCode getArzneimittelName(MedicationStatement resource) {
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
                return mapArzneimittelName(coding);
            }
        }
        throw new ConversionException("The MedicationStatement is missing the medication");
    }

    private ArzneimittelNameDefiningCode mapArzneimittelName(Coding coding) {
        Map<String, ArzneimittelNameDefiningCode> arzneimittelNameDefiningCodeMap = ArzneimittelNameDefiningCode.getCodesAsMap();
        if (arzneimittelNameDefiningCodeMap.containsKey(coding.getCode())) {
            return arzneimittelNameDefiningCodeMap.get(coding.getCode());
        }
        throw new ConversionException("Invalid medicationCodeableConcept code " + coding.getCode());
    }

}
