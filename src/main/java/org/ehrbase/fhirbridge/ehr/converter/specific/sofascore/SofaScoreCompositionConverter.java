package org.ehrbase.fhirbridge.ehr.converter.specific.sofascore;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class SofaScoreCompositionConverter extends ObservationToCompositionConverter<SOFAComposition> {

    private final SofaScoreObservationConverter sofaScoreObservationConverter = new SofaScoreObservationConverter();

    @Override
    public SOFAComposition convertInternal(@NonNull Observation resource) {
        SOFAComposition composition = new SOFAComposition();
        composition.setSofaScore(sofaScoreObservationConverter.convert(resource));
        return composition;
    }
}
