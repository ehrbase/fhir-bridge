package org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;

import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.KoerpergewichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import java.util.ArrayList;
import java.util.List;

public class BodyWeightCompositionConverter extends ObservationToCompositionConverter<KoerpergewichtComposition> {

    @Override
    protected KoerpergewichtComposition convertInternal(Observation resource) {
        KoerpergewichtComposition composition = new KoerpergewichtComposition();
        mapKategorie(composition, resource);
        if(resource.hasValueQuantity()){
            composition.setKoerpergewicht(new KoerpergewichtObservationConverter().convert(resource));
        }
        return composition;
    }

    private void mapKategorie(KoerpergewichtComposition composition, Observation resource) {
        List<KoerpergewichtKategorieElement> list = new ArrayList<>();
        for (CodeableConcept category : resource.getCategory()) {
            for (Coding coding : category.getCoding()) {
                KoerpergewichtKategorieElement koerpergewichtTestKategorieElement = new KoerpergewichtKategorieElement();
                koerpergewichtTestKategorieElement.setValue(coding.getCode());
                list.add(koerpergewichtTestKategorieElement);
            }
        }
        composition.setKategorie(list);
    }
}
