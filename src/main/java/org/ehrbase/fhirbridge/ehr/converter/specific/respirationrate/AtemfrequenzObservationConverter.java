package org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

public class AtemfrequenzObservationConverter extends ObservationToObservationConverter<AtemfrequenzObservation> {
    @Override
    protected AtemfrequenzObservation convertInternal(Observation resource) {
        AtemfrequenzObservation observation = new AtemfrequenzObservation();
        observation.setMesswertMagnitude(resource.getValueQuantity().getValue().doubleValue());
        observation.setMesswertUnits(resource.getValueQuantity().getCode());
        return observation;
    }
}
