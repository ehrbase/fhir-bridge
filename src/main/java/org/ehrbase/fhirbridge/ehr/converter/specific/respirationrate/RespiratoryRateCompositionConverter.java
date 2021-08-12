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
        mapKategorie(resource).ifPresent(composition::setKategorieValue);
        composition.setAtemfrequenz(new AtemfrequenzObservationConverter().convert(resource));
        return composition;
    }

    private Optional<String> mapKategorie( Observation resource) {
        return resource.getCategory().get(0).getCoding().stream()
                .map(Coding::getCode)
                .findFirst();
        }

}
