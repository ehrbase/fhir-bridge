package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusKategorieElement;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RaucherstatusCompositionConverter extends ObservationToCompositionConverter<RaucherstatusComposition> {

    @Override
    public RaucherstatusComposition convertInternal(@NonNull Observation resource) {
        RaucherstatusComposition composition = new RaucherstatusComposition();
        if (resource.getCategory().get(0).hasCoding()) {
            composition.setKategorie(mapKategorie(resource));
        }
        composition.setRaucherstatus(new RaucherstatusEvaluationConverter().convert(resource));
        return composition;
    }

    private List<RaucherstatusKategorieElement> mapKategorie(Observation resource) {
        List<RaucherstatusKategorieElement> raucherstatusKategorieElementList = new ArrayList<>();
        for (Coding coding : resource.getCategory().get(0).getCoding()) {
            RaucherstatusKategorieElement raucherstatusKategorieElement = new RaucherstatusKategorieElement();
            raucherstatusKategorieElement.setValue(coding.getCode());
            raucherstatusKategorieElementList.add(raucherstatusKategorieElement);
        }
        return raucherstatusKategorieElementList;
    }

}
