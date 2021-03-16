package org.ehrbase.fhirbridge.ehr.converter.specific;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class HeartRateCompositionConverter extends ObservationToCompositionConverter<HerzfrequenzComposition> {

    @Override
    public HerzfrequenzComposition convertInternal(@NonNull Observation resource) {
        //create result and observation objects
        HerzfrequenzComposition composition = new HerzfrequenzComposition();
        HerzfrequenzObservation observation = new HerzfrequenzObservation();
        //map values of interest from FHIR observation
        try {
            observation.setFrequenzMagnitude(resource.getValueQuantity().getValue().doubleValue());
            observation.setFrequenzUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }
        composition.setHerzfrequenz(observation);
        return composition;
    }
}
