package org.ehrbase.fhirbridge.ehr.converter.specific.fio2;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeatmungswerteKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FiO2CompositionConverter extends ObservationToCompositionConverter<BeatmungswerteComposition> {

    @Override
    public BeatmungswerteComposition convertInternal(@NonNull Observation resource) {
        BeatmungswerteComposition composition = new BeatmungswerteComposition();
        mapKategorie(composition, resource);
        composition.setBeobachtungenAmBeatmungsgeraet(new BeobachtungenAmBeatmungsgeraetObservationConverter().convert(resource));
        return composition;
    }

    private void mapKategorie(BeatmungswerteComposition composition, Observation resource) {
        List<BeatmungswerteKategorieElement> list = new ArrayList<>();
        for (CodeableConcept category : resource.getCategory()) {
            for (Coding coding : category.getCoding()) {
                BeatmungswerteKategorieElement beatmungswerteKategorieElement = new BeatmungswerteKategorieElement();
                beatmungswerteKategorieElement.setValue(coding.getCode());
                list.add(beatmungswerteKategorieElement);
            }
        }
        composition.setKategorie(list);
    }
}
