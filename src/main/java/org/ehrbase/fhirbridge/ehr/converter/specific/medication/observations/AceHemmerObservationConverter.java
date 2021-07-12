package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ArzneimittelNameDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;

public class AceHemmerObservationConverter extends GeccoMedikationObservationConverter<AceHemmerObservation> {

    @Override
    protected AceHemmerObservation convertInternal(MedicationStatement resource) {
        AceHemmerObservation aceHemmerObservation = new AceHemmerObservation();
        aceHemmerObservation.setArzneimittelNameDefiningCode(getArzneimittelName(resource));
        getGrundDefiningCode(resource).ifPresent(aceHemmerObservation::setGrundDefiningCode);
        return aceHemmerObservation;
    }

    private ArzneimittelNameDefiningCode2 getArzneimittelName(MedicationStatement resource) {
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.DIMDI_ATC.getUrl())) {
                return mapArzneimittelName(coding);
            }
        }
        throw new ConversionException("The MedicationStatement is missing the medication");
    }

    private ArzneimittelNameDefiningCode2 mapArzneimittelName(Coding coding) {
        Map<String, ArzneimittelNameDefiningCode2> arzneimittelNameDefiningCodeMap = ArzneimittelNameDefiningCode2.getCodesAsMap();
        if (arzneimittelNameDefiningCodeMap.containsKey(coding.getCode())) {
            return arzneimittelNameDefiningCodeMap.get(coding.getCode());
        }
        throw new ConversionException("Invalid medicationCodeableConcept code " + coding.getCode());
    }
}
