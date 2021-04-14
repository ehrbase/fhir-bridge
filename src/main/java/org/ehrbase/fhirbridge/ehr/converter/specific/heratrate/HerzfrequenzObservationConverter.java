package org.ehrbase.fhirbridge.ehr.converter.specific.heratrate;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

public class HerzfrequenzObservationConverter extends ObservationToObservationConverter<HerzfrequenzObservation> {
    @Override
    protected HerzfrequenzObservation convertInternal(Observation resource) {
        HerzfrequenzObservation observation = new HerzfrequenzObservation();
        //map values of interest from FHIR observation
        try {
            observation.setFrequenzMagnitude(resource.getValueQuantity().getValue().doubleValue());
            observation.setFrequenzUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }
        return observation;
    }
}
