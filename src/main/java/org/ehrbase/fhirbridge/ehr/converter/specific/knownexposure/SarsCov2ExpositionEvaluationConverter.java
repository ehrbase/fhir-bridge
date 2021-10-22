package org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
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
        if(fhirObserv.hasValueCodeableConcept() && fhirObserv.getValueCodeableConcept().getCoding().get(0).getCode().equals("840546002")) {
            sarsCov2ExpositionEvaluation.setDatumUhrzeitDerExpositionValue(TimeConverter.convertObservationTime(fhirObserv));
        }
    }

    private void mapInfektionserreger(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation) {
        sarsCov2ExpositionEvaluation.setInfektionserregerValue("SARS-CoV-2");
    }

    private void determineAndMapExposition(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation fhirObserv) {
        if (fhirObserv.hasDataAbsentReason()) {
            sarsCov2ExpositionEvaluation.setExpositionVorhandenNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        } else if (fhirObserv.hasValueCodeableConcept()) {
            mapExpositionPresent(sarsCov2ExpositionEvaluation, fhirObserv);
        }
    }

    private void mapExpositionPresent(SarsCov2ExpositionEvaluation sarsCov2ExpositionEvaluation, Observation observation) {
        for (Coding coding : observation.getValueCodeableConcept().getCoding()) {
            DvCodedTextParser.getInstance()
                    .parseFHIRCoding(coding)
                    .ifPresent(sarsCov2ExpositionEvaluation::setExpositionVorhanden);
        }
    }
}