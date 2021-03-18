package org.ehrbase.fhirbridge.ehr.converter.knownexposure;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.ExpositionVorhandenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionEvaluation;
import org.hl7.fhir.r4.model.Observation;

public class SarsCov2ExpositionConverter {

    public SarsCov2ExpositionEvaluation map(Observation fhirObserv) {
        SarsCov2ExpositionEvaluation eval = new SarsCov2ExpositionEvaluation();

        eval.setLanguage(Language.DE);
        eval.setSubject(new PartySelf());

        mapInfektionserreger(eval, fhirObserv);
        mapExpositionVorhanden(eval, fhirObserv);
        mapSpezifischeDetailsZurExposition(eval, fhirObserv);

        return eval;
    }

    private void mapSpezifischeDetailsZurExposition(SarsCov2ExpositionEvaluation eval, Observation fhirObserv) {
        eval.setBeschreibungDerExpositionValue("Kontakt zu COVID-19 erkrankter Person");
        eval.setDatumUhrzeitDerExpositionValue(fhirObserv.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
    }

    private void mapInfektionserreger(SarsCov2ExpositionEvaluation eval, Observation fhirObserv) {
        eval.setInfektionserregerValue("SARS-CoV-2");
    }

    private void mapExpositionVorhanden(SarsCov2ExpositionEvaluation eval, Observation fhirObserv) {

        Boolean CodeForExpositionExists = isCodeForKnownExpositionExistent(fhirObserv);

        if (CodeForExpositionExists) {
            mapExpositionCode(eval, fhirObserv);
        } else {
            mapDataAbsentReasonAsUnknownExposition(eval, fhirObserv);
        }

    }

    private Boolean isCodeForKnownExpositionExistent(Observation fhirObserv) {

        int numElements = fhirObserv.getValueCodeableConcept().getCoding().size();
        switch (numElements) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                throw new UnprocessableEntityException("Multiple known expositions reasons are not possible in SarsCov2ExpositionConverter!");
        }
}

    private void mapDataAbsentReasonAsUnknownExposition(SarsCov2ExpositionEvaluation eval, Observation fhirObserv) {
        int numElements = fhirObserv.getDataAbsentReason().getCoding().size();

        if (1 != numElements) {
            throw new UnprocessableEntityException("Zero or Multiple data absent reasons are not possible in SarsCov2ExpositionConverter with no valid exposition information!");
        }
        String code = fhirObserv.getDataAbsentReason().getCoding().get(0).getCode();
        String system = fhirObserv.getDataAbsentReason().getCoding().get(0).getSystem();

        if (code.equals(KnownExposureCode.UNKNOWN_EXPOSURE.getCode()) &&
            system.equals(KnownExposureCode.UNKNOWN_EXPOSURE.getSystem())) {
            eval.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.UNKNOWN);
        } else {
            throw new UnprocessableEntityException("Cannot set 'unknown' in SarsCov2ExpositionConverter with no valid data absent reason coding!");
        }
    }

    private void mapExpositionCode(SarsCov2ExpositionEvaluation eval, Observation fhirObserv) {

        String code = fhirObserv.getValueCodeableConcept().getCoding().get(0).getCode();
        String system = fhirObserv.getValueCodeableConcept().getCoding().get(0).getSystem();

        if (code.equals(KnownExposureCode.KNOWN_EXPOSURE.getCode()) &&
                system.equals(KnownExposureCode.KNOWN_EXPOSURE.getSystem())) {
            eval.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.EXPOSURE_TO_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_EVENT);
        } else if (code.equals(KnownExposureCode.NO_EXPOSURE.getCode()) &&
                system.equals(KnownExposureCode.NO_EXPOSURE.getSystem())) {
            eval.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.NO_QUALIFIER_VALUE);
        } else {
            throw new UnprocessableEntityException("The SNOMED code '" + code + "' in system '"+system+"' is not supported for known SarsCov2ExpositionConverter!");
        }
    }

}
