package org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.ExpositionVorhandenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;


public class SarsCov2ExpositionEvaluationConverter extends ObservationToEvaluationConverter<SarsCov2ExpositionEvaluation> {

    @Override
    protected SarsCov2ExpositionEvaluation convertInternal(Observation fhirObserv) {
        SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation = new SarsCov2ExpositionEvaluation();

        mapInfektionserreger(sarsCov2ExpositionEvaluation);
        determineAndMapExposition(sarsCov2ExpositionEvaluation, fhirObserv);
        mapSpezifischeDetailsZurExposition(sarsCov2ExpositionEvaluation, fhirObserv);

        return sarsCov2ExpositionEvaluation;
    }

    private void mapSpezifischeDetailsZurExposition(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {
        sarsCov2ExpositionEvaluation.setBeschreibungDerExpositionValue("Kontakt zu COVID-19 erkrankter Person");
        sarsCov2ExpositionEvaluation.setDatumUhrzeitDerExpositionValue(TimeConverter.convertObservationTime(fhirObserv));
    }

    private void mapInfektionserreger(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation) {
        sarsCov2ExpositionEvaluation.setInfektionserregerValue("SARS-CoV-2");
    }

    private void determineAndMapExposition(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {
        if (fhirObserv.hasDataAbsentReason()) {
            sarsCov2ExpositionEvaluation.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.UNKNOWN);
        } else if (fhirObserv.hasValueCodeableConcept()) {
            mapExpositionPresent(sarsCov2ExpositionEvaluation, fhirObserv);
        }
    }

    private void mapExpositionPresent(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {
        for (Coding coding : fhirObserv.getValueCodeableConcept().getCoding()) {
            if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
                convertExposition(coding, sarsCov2ExpositionEvaluation);
            } else {
                throw new ConversionException("ValueCodeableCodeConcept.coding.system has to be SNOMED");
            }
        }
    }

    private void convertExposition(Coding coding, SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation) {
        String code = coding.getCode();
        if (ExpositionVorhandenDefiningCode.EXPOSURE_TO_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_EVENT.getCode().equals(code)) {
            sarsCov2ExpositionEvaluation.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.EXPOSURE_TO_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_EVENT);
        } else if (ExpositionVorhandenDefiningCode.NO_QUALIFIER_VALUE.getCode().equals(code)) {
            sarsCov2ExpositionEvaluation.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.NO_QUALIFIER_VALUE);
        } else {
            throw new ConversionException("Unsupported code provided! Supported ones are 840546002 or 373067005");
        }
    }

}


