package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PulseOximetryCompositionConverter extends ObservationToCompositionConverter<PulsoxymetrieComposition> {

    @Override
    public PulsoxymetrieComposition convertInternal(@NonNull Observation resource) {
        new PulseOximetryCodeChecker().checkIsPulsOximetry(resource);
        PulsoxymetrieComposition composition = new PulsoxymetrieComposition();
        composition.setKategorie(mapKategorie(resource));
        composition.setPulsoxymetrie(new PulsoxymetrieObservationConverter().convert(resource));
        return composition;
    }

    private List<PulsoxymetrieKategorieElement> mapKategorie(Observation observation) {
        List<PulsoxymetrieKategorieElement> pulsoxymetrieKategorieElementList = new ArrayList<>();
        for(CodeableConcept codeableConcept : observation.getCategory()){
            if(codeableConcept.hasCoding()){
                for(Coding coding : codeableConcept.getCoding()){
                    PulsoxymetrieKategorieElement pulsoxymetrieKategorieElement = new PulsoxymetrieKategorieElement();
                    pulsoxymetrieKategorieElement.setValue(coding.getCode());
                    pulsoxymetrieKategorieElementList.add(pulsoxymetrieKategorieElement);
                }
            }
        }
        return pulsoxymetrieKategorieElementList;
    }

}
