package org.ehrbase.fhirbridge.ehr.converter.specific.sexatbirth;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeccoPersonendatenKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SexAtBirthConverter extends ObservationToCompositionConverter<GECCOPersonendatenComposition> {

    @Override
    public GECCOPersonendatenComposition convertInternal(@NonNull Observation observation) {
        GECCOPersonendatenComposition geccoPersonendatenComposition = new GECCOPersonendatenComposition();
        geccoPersonendatenComposition.setKategorie(mapKategorie(observation));
        geccoPersonendatenComposition.setGeschlecht(new GeschlechtEvaluationConverter().convert(observation));
        return geccoPersonendatenComposition;
    }

    private List<GeccoPersonendatenKategorieElement> mapKategorie(Observation resource) {
        List<GeccoPersonendatenKategorieElement> geccoPersonendatenKategorieElementList = new ArrayList<>();
        for (CodeableConcept codeableConcept : resource.getCategory()) {
            for (Coding coding : codeableConcept.getCoding()) {
                GeccoPersonendatenKategorieElement geccoPersonendatenKategorieElement = new GeccoPersonendatenKategorieElement();
                geccoPersonendatenKategorieElement.setValue(coding.getCode());
                geccoPersonendatenKategorieElementList.add(geccoPersonendatenKategorieElement);
            }
        }
        return geccoPersonendatenKategorieElementList;
    }

}
