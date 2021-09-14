package org.ehrbase.fhirbridge.ehr.converter.specific.fio2;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class FiO2CompositionConverter extends ObservationToCompositionConverter<BeatmungswerteComposition> {

    @Override
    public BeatmungswerteComposition convertInternal(@NonNull Observation resource) {
        BeatmungswerteComposition composition = new BeatmungswerteComposition();
        composition.setBeobachtungenAmBeatmungsgeraet(new BeobachtungenAmBeatmungsgeraetObservationConverter().convert(resource));
        return composition;
    }
}
