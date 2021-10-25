package org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.SARSCoV2ExpositionComposition;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SarsCov2KnownExposureCompositionConverter extends ObservationToCompositionConverter<SARSCoV2ExpositionComposition> {

    @Override
    public SARSCoV2ExpositionComposition convertInternal(@NonNull Observation resource) {
        SARSCoV2ExpositionComposition composition = new SARSCoV2ExpositionComposition();
        mapKategorie(composition, resource);
        composition.setSarsCov2Exposition(new SarsCov2ExpositionEvaluationConverter().convert(resource));
        return composition;
    }

    private void mapKategorie(SARSCoV2ExpositionComposition composition, Observation resource) {
        List<SarsCov2ExpositionKategorieElement> list = new ArrayList<>();
        for (CodeableConcept category : resource.getCategory()) {
            for (Coding coding : category.getCoding()) {
                SarsCov2ExpositionKategorieElement sarsCov2ExpositionKategorieElement = new SarsCov2ExpositionKategorieElement();
                sarsCov2ExpositionKategorieElement.setValue(coding.getCode());
                list.add(sarsCov2ExpositionKategorieElement);
            }
        }
        composition.setKategorie(list);
    }
}
