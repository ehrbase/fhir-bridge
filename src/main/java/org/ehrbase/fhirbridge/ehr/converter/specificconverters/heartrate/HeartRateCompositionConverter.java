package org.ehrbase.fhirbridge.ehr.converter.specificconverters.heartrate;

import org.ehrbase.fhirbridge.ehr.converter.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.hl7.fhir.r4.model.Observation;

public class HeartRateCompositionConverter extends ObservationToCompositionConverter<HerzfrequenzComposition> {
    @Override
    protected HerzfrequenzComposition convertInternal(Observation resource) {
        HerzfrequenzComposition result = new HerzfrequenzComposition();
        result.setHerzfrequenz(new HerzfrequenzObservationConverter().convert(resource));
        return result;
    }
}
