package org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class AtemfrequenzObservationConverter extends ObservationToObservationConverter<AtemfrequenzObservation> {
    @Override
    protected AtemfrequenzObservation convertInternal(Observation resource) {
        AtemfrequenzObservation observation = new AtemfrequenzObservation();
        mapMagnitude(resource).ifPresent(observation::setMesswertMagnitude);
        mapUnit(resource).ifPresent(observation::setMesswertUnits);
        observation.setMesswertMagnitude(resource.getValueQuantity().getValue().doubleValue());
        observation.setMesswertUnits(resource.getValueQuantity().getCode());
        return observation;
    }

    private Optional<String> mapUnit(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getUnit());
        }
        return Optional.empty();
    }

    private Optional<Double> mapMagnitude(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        }
        return Optional.empty();
    }
}
