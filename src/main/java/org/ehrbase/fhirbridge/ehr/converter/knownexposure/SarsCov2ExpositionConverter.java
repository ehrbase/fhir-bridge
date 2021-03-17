package org.ehrbase.fhirbridge.ehr.converter.knownexposure;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.ExpositionVorhandenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionEvaluation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class SarsCov2ExpositionConverter {

    public SarsCov2ExpositionEvaluation  map(Observation fhirObserv) {
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

        //BSa valueCodeableConcept can be not existing -> how to deal with that?
        Optional<CodeableConcept> valueCodeableConcept;
        valueCodeableConcept = Optional.ofNullable(fhirObserv.getValueCodeableConcept());

        if (valueCodeableConcept.isPresent()) {
            return;
        }

        String code = fhirObserv.getValueCodeableConcept().getCoding().get(0).getCode();

        if (code.equals(KnownExposureCode.KNOWN_EXPOSURE.getCode())) {
            eval.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.EXPOSURE_TO_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_EVENT);
        } else if (code.equals(KnownExposureCode.NO_EXPOSURE.getCode())) {
            eval.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.NO_QUALIFIER_VALUE);
        } else if (code.equals(KnownExposureCode.UNKNOWN_EXPOSURE.getCode())) {
            eval.setExpositionVorhandenDefiningCode(ExpositionVorhandenDefiningCode.UNKNOWN);
        } else {
            throw new UnprocessableEntityException("The SNOMED code " + code + " is not supported for known SarsCov2ExpositionConverter !");
        }
    }

}
