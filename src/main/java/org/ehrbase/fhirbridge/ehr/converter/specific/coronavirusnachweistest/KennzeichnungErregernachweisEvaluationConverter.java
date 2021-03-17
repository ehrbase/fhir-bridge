package org.ehrbase.fhirbridge.ehr.converter.specific.coronavirusnachweistest;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.hl7.fhir.r4.model.Observation;

public class KennzeichnungErregernachweisEvaluationConverter extends EntryEntityConverter<Observation, KennzeichnungErregernachweisEvaluation> {
    @Override
    protected KennzeichnungErregernachweisEvaluation convertInternal(Observation resource) {
        return null;
    }
}
