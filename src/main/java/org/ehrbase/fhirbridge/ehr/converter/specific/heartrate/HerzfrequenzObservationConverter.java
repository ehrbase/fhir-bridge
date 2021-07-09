package org.ehrbase.fhirbridge.ehr.converter.specific.heartrate;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;
import java.util.Optional;

public class HerzfrequenzObservationConverter extends ObservationToObservationConverter<HerzfrequenzObservation> {

    @Override
    protected HerzfrequenzObservation convertInternal(Observation resource) {
        HerzfrequenzObservation herzfrequenzObservation = new HerzfrequenzObservation();
        getValue(resource).ifPresent(herzfrequenzObservation::setFrequenzMagnitude);
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
