package org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.KoerpergroesseKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BodyHeightCompositionConverter extends ObservationToCompositionConverter<KoerpergroesseComposition> {

    @Override
    public KoerpergroesseComposition convertInternal(@NonNull Observation resource) {
        KoerpergroesseComposition composition = new KoerpergroesseComposition();
        mapKategorie(composition, resource);
        composition.setGroesseLaenge(new GroesseLaengeObservationConverter().convert(resource));
        return composition;
    }

    private void mapKategorie(KoerpergroesseComposition composition, Observation resource) {
        List<KoerpergroesseKategorieElement> list = new ArrayList<>();
        for (CodeableConcept category : resource.getCategory()) {
            for (Coding coding : category.getCoding()) {
                KoerpergroesseKategorieElement koerpergroesseKategorieElement = new KoerpergroesseKategorieElement();
                koerpergroesseKategorieElement.setValue(coding.getCode());
                list.add(koerpergroesseKategorieElement);
            }
        }
        composition.setKategorie(list);
    }

}
