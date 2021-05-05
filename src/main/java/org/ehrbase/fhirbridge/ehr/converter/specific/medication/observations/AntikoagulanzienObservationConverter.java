package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ArzneimittelNameDefiningCode4;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.GrundDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;
import java.util.Optional;

public class AntikoagulanzienObservationConverter extends GeccoMedikationObservationConverter<AntikoagulanzienObservation> {

    @Override
    protected AntikoagulanzienObservation convertInternal(MedicationStatement resource) {
        AntikoagulanzienObservation antikoagulanzienObservation = new AntikoagulanzienObservation();
        antikoagulanzienObservation.setArzneimittelNameDefiningCode(getArzneimittelName(resource));
        getGrundDefiningCode(resource).ifPresent(antikoagulanzienObservation::setGrundDefiningCode);
        return antikoagulanzienObservation;
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
        throw new UnprocessableEntityException("Invalid medicationCodeableConcept code " + coding.getCode());
    }

    protected Optional<GrundDefiningCode> getGrundDefiningCode(MedicationStatement resource) {
        for (Coding coding : resource.getReasonCode().get(0).getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
                return mapGrundDefiningCode(coding);
            }
        }
        return Optional.empty();
    }

    protected Optional<GrundDefiningCode> mapGrundDefiningCode(Coding coding) {
        String snomedCode = coding.getCode();
        if (snomedCode.equals(GrundDefiningCode.ADJUNCT_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.ADJUNCT_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.ADJUVANT_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.ADJUVANT_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.CURATIVE_PROCEDURE_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.CURATIVE_PROCEDURE_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.NEO_ADJUVANT_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.NEO_ADJUVANT_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.PROPHYLAXIS_PROCEDURE_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.PROPHYLAXIS_PROCEDURE_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.SUPPORTIVE_PROCEDURE_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.SUPPORTIVE_PROCEDURE_INTENT_QUALIFIER_VALUE);
        } else {
          throw new UnprocessableEntityException("The reasonCode "+snomedCode+" is invalid !");
        }
    }
}
