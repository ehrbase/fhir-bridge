package org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;

public class ClinicalFrailtyScaleScoreCompositionConverter extends ObservationToCompositionConverter<KlinischeFrailtySkalaComposition> {

    @Override
    public KlinischeFrailtySkalaComposition convertInternal(Observation resource) {
        KlinischeFrailtySkalaComposition result = new KlinischeFrailtySkalaComposition();
        mapKategorie(result, resource);
        if(resource.hasValueCodeableConcept()){
            result.setKlinischeFrailtySkalaCfs(new KlinischeFrailtySkalaObservationConverter().convert(resource));
        }
        return result;
    }

    private void mapKategorie(KlinischeFrailtySkalaComposition composition, Observation resource) {
        List<KlinischeFrailtySkalaKategorieElement> list = new ArrayList<>();
        for (CodeableConcept category : resource.getCategory()) {
            for (Coding coding : category.getCoding()) {
                KlinischeFrailtySkalaKategorieElement klinischeFrailtySkalaKategorieElement = new KlinischeFrailtySkalaKategorieElement();
                klinischeFrailtySkalaKategorieElement.setValue(coding.getCode());
                list.add(klinischeFrailtySkalaKategorieElement);
            }
        }
        composition.setKategorie(list);
    }
}
