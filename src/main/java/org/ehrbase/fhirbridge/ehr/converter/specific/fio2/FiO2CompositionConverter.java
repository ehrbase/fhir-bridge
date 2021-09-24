package org.ehrbase.fhirbridge.ehr.converter.specific.fio2;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeatmungswerteKategorieElement;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FiO2CompositionConverter extends ObservationToCompositionConverter<BeatmungswerteComposition> {

    @Override
    public BeatmungswerteComposition convertInternal(@NonNull Observation resource) {
        BeatmungswerteComposition composition = new BeatmungswerteComposition();
        composition.setKategorie(mapKategorie(resource));
        composition.setBeobachtungenAmBeatmungsgeraet(new BeobachtungenAmBeatmungsgeraetObservationConverter().convert(resource));
        return composition;
    }

    private List<BeatmungswerteKategorieElement> mapKategorie(Observation resource) {
        List<BeatmungswerteKategorieElement> beatmungswerteKategorieElements = new ArrayList<>();
        for (Coding coding : resource.getCategory().get(0).getCoding()) {
            BeatmungswerteKategorieElement beatmungswerteKategorieElement = new BeatmungswerteKategorieElement();
            beatmungswerteKategorieElement.setValue(coding.getCode());
            beatmungswerteKategorieElements.add(beatmungswerteKategorieElement);
        }
        return beatmungswerteKategorieElements;
    }

}
