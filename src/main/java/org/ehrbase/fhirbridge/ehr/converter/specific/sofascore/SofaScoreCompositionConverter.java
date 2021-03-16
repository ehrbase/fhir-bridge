package org.ehrbase.fhirbridge.ehr.converter.specific.sofascore;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class SofaScoreCompositionConverter extends CompositionConverter<Observation, SOFAComposition> {

    private final SofaScoreObservationConverter observationConverter = new SofaScoreObservationConverter();

    @Override
    public SOFAComposition convertInternal(@NonNull Observation resource) {
        SOFAComposition composition = new SOFAComposition();
        composition.setSofaScore(observationConverter.convert(resource));
        return composition;
    }
}
