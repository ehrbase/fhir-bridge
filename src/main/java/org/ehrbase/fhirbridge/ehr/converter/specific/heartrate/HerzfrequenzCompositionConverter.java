package org.ehrbase.fhirbridge.ehr.converter.specific.heartrate;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.RegistereintragKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HerzfrequenzCompositionConverter extends ObservationToCompositionConverter<HerzfrequenzComposition> {
    @Override
    protected HerzfrequenzComposition convertInternal(Observation resource) {
        HerzfrequenzComposition result = new HerzfrequenzComposition();
        result.setPulsfrequenzHerzfrequenz(new PulsfrequenzHerzfrequenzObservationConverter().convert(resource));
        result.setKategorie(convertCategory(resource));
        return result;
    }

    private List<RegistereintragKategorieElement> convertCategory(Observation resource) {
        if (!resource.hasCategory()) {
            return new ArrayList<>();
        }
        return resource.getCategory()
                .stream()
                .filter(CodeableConcept::hasCoding)
                .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                .map(Coding::getCode)
                .map(code -> {
                    RegistereintragKategorieElement element = new RegistereintragKategorieElement();
                    element.setValue(code);
                    return element;
                })
                .collect(Collectors.toList());
    }
}



