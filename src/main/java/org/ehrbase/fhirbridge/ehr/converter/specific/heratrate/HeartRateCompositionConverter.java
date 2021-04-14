package org.ehrbase.fhirbridge.ehr.converter.specific.heratrate;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class HeartRateCompositionConverter extends ObservationToCompositionConverter<HerzfrequenzComposition> {

    @Override
    public HerzfrequenzComposition convertInternal(@NonNull Observation resource) {
        HerzfrequenzComposition composition = new HerzfrequenzComposition();
        composition.setHerzfrequenz(new HerzfrequenzObservationConverter().convert(resource));
        return composition;
    }
}
