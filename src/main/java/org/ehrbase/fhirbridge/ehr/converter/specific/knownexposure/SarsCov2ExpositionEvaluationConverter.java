package org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.ExpositionVorhandenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.KnownExposureCode;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionEvaluation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public class SarsCov2ExpositionEvaluationConverter extends ObservationToEvaluationConverter<SarsCov2ExpositionEvaluation> {

    @Override
    protected SarsCov2ExpositionEvaluation convertInternal(Observation fhirObserv) {
        SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation = new SarsCov2ExpositionEvaluation();

        mapInfektionserreger(sarsCov2ExpositionEvaluation);
        mapExpositionVorhanden(sarsCov2ExpositionEvaluation, fhirObserv);
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

    private void mapExpositionVorhanden(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {
        for(Coding coding : fhirObserv.getValueCodeableConcept().getCoding()){
            if(coding.getSystem().equals(CodeSystem.SNOMED.getUrl())){
                convertExposition(coding, sarsCov2ExpositionEvaluation, fhirObserv);
            }else{
                throw new ConversionException("ValueCodeableCodeConcept.coding.system has to be SNOMED");
            }
        }
    }

    private void convertExposition(Coding coding, SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {
        switch(coding.getCode()){
            case "840546002":
                mapExpositionCode(sarsCov2ExpositionEvaluation, fhirObserv);
                break;
            case "373067005":
                mapDataAbsentReasonAsUnknownExposition(sarsCov2ExpositionEvaluation, fhirObserv);
            default:
                throw new ConversionException("Unsupported code provided! Supported ones are 840546002 or 373067005");
        }
    }

    private void mapDataAbsentReasonAsUnknownExposition(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {

        checkForAvailabilityOfDataAbsentReason(fhirObserv);

        String code = fhirObserv.getDataAbsentReason().getCoding().get(0).getCode();
        String system = fhirObserv.getDataAbsentReason().getCoding().get(0).getSystem();

        if (code.equals(KnownExposureCode.UNKNOWN_EXPOSURE.getCode()) &&
                system.equals(KnownExposureCode.UNKNOWN_EXPOSURE.getSystem())) {
            sarsCov2ExpositionEvaluation.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.UNKNOWN);
        } else {
            throw new UnprocessableEntityException("Cannot set 'unknown' in SarsCov2ExpositionConverter with no valid data absent reason coding!");
        }
    }

    private void checkForAvailabilityOfDataAbsentReason(Observation fhirObserv) {
        if(fhirObserv.hasDataAbsentReason()){
            Optional<CodeableConcept> optionalCC = Optional.of(fhirObserv.getDataAbsentReason());

            if (!optionalCC.isPresent()) {
                throw new UnprocessableEntityException("Zero data absent reasons are not possible in SarsCov2ExpositionConverter with no valid exposition information!");
            }

            if (fhirObserv.hasValueCodeableConcept() && optionalCC.get().hasCoding()) {
                checkForMultipleAbsentReasons(fhirObserv);
            }
        }
    }


    private void checkForMultipleAbsentReasons(Observation fhirObserv) {
        int numElements = fhirObserv.getDataAbsentReason().getCoding().size();

        if (1 != numElements) {
            throw new UnprocessableEntityException("Multiple data absent reasons are not possible in SarsCov2ExpositionConverter with no valid exposition information!");
        }
    }

    private void mapExpositionCode(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {

        String code = fhirObserv.getValueCodeableConcept().getCoding().get(0).getCode();
        String system = fhirObserv.getValueCodeableConcept().getCoding().get(0).getSystem();

        if (code.equals(KnownExposureCode.KNOWN_EXPOSURE.getCode()) &&
                system.equals(KnownExposureCode.KNOWN_EXPOSURE.getSystem())) {
            sarsCov2ExpositionEvaluation.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.EXPOSURE_TO_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_EVENT);
        } else if (code.equals(KnownExposureCode.NO_EXPOSURE.getCode()) &&
                system.equals(KnownExposureCode.NO_EXPOSURE.getSystem())) {
            sarsCov2ExpositionEvaluation.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.NO_QUALIFIER_VALUE);
        } else {
            throw new UnprocessableEntityException("The SNOMED code '" + code + "' in system '" + system + "' is not supported for known SarsCov2ExpositionConverter!");
        }
    }
}


