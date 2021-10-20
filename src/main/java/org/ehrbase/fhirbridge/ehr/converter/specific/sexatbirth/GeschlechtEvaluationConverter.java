package org.ehrbase.fhirbridge.ehr.converter.specific.sexatbirth;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class GeschlechtEvaluationConverter extends ObservationToEvaluationConverter<GeschlechtEvaluation> {

    @Override
    public GeschlechtEvaluation convertInternal(@NonNull Observation observation) {
        GeschlechtEvaluation geschlechtEvaluation = new GeschlechtEvaluation();
        if (observation.getValueCodeableConcept().hasCoding()) {
            DvCodedTextParser.getInstance().parseFHIRCoding(observation.getValueCodeableConcept().getCoding().get(0)).ifPresent(geschlechtEvaluation::setGeschlechtBeiDerGeburt);
        }
        return geschlechtEvaluation;
    }

}
