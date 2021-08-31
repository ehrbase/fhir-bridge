package org.ehrbase.fhirbridge.ehr.converter.specific.heartrate;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.RegisterEntryCategoryElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;

public class HerzfrequenzCompositionConverter extends ObservationToCompositionConverter<HerzfrequenzComposition> {
    @Override
    protected HerzfrequenzComposition convertInternal(Observation resource) {
        HerzfrequenzComposition result = new HerzfrequenzComposition();
        mapKategorie(result, resource);
        result.setPulseHeartBeat(new HerzfrequenzObservationConverter().convert(resource));
        return result;
    }

    private void mapKategorie(HerzfrequenzComposition composition, Observation resource) {
        List<RegisterEntryCategoryElement> list = new ArrayList<>();
        for (CodeableConcept category : resource.getCategory()) {
            for (Coding coding : category.getCoding()) {
                RegisterEntryCategoryElement registerEntryCategoryElement = new RegisterEntryCategoryElement();
                registerEntryCategoryElement.setValue(coding.getCode());
                list.add(registerEntryCategoryElement);
            }
        }
        composition.setCategory(list);
    }
}
