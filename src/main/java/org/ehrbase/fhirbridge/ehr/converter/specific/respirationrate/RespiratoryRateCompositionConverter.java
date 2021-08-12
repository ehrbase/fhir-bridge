package org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class RespiratoryRateCompositionConverter extends ObservationToCompositionConverter<AtemfrequenzComposition> {

    @Override
    public AtemfrequenzComposition convertInternal(@NonNull Observation resource) {
        AtemfrequenzComposition composition = new AtemfrequenzComposition();
        if (resource.getCategory().get(0).hasCoding()) {
            composition.setKategorie(mapKategorie(resource));
        }
        composition.setAtemfrequenz(new AtemfrequenzObservationConverter().convert(resource));
        return composition;
    }

    private List<RegistereintragKategorieElement> mapKategorie(Observation resource) {
        List<RegistereintragKategorieElement> registereintragKategorieElements = new ArrayList<>();
        for (Coding coding : resource.getCategory().get(0).getCoding()) {
            RegistereintragKategorieElement registereintragKategorieElement = new RegistereintragKategorieElement();
            registereintragKategorieElement.setValue(coding.getCode());
            registereintragKategorieElements.add(registereintragKategorieElement);
        }
        return registereintragKategorieElements;
    }
}
