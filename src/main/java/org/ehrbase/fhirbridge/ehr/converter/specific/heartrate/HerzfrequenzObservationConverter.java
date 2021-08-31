package org.ehrbase.fhirbridge.ehr.converter.specific.heartrate;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.PulseHeartBeatObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class HerzfrequenzObservationConverter extends ObservationToObservationConverter<PulseHeartBeatObservation> {

    @Override
    protected PulseHeartBeatObservation convertInternal(Observation resource) {
        PulseHeartBeatObservation herzfrequenzObservation = new PulseHeartBeatObservation();
        getValue(resource).ifPresent(herzfrequenzObservation::setRateMagnitude);
        getUnit(resource).ifPresent(herzfrequenzObservation::setRateUnits);
        return herzfrequenzObservation;
    }

    private Optional<Double> getValue(Observation resource) {
        if(resource.hasValueQuantity() ){
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        }else{
            return Optional.empty();
        }
    }

    private Optional<String> getUnit(Observation resource) {
        if(resource.hasValueQuantity() ){
            return Optional.of(resource.getValueQuantity().getCode());
        }else{
            return Optional.empty();
        }
    }
}
