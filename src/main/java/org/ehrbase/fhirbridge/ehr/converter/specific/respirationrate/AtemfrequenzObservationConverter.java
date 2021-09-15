package org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class AtemfrequenzObservationConverter extends ObservationToObservationConverter<AtemfrequenzObservation> {
    @Override
    protected AtemfrequenzObservation convertInternal(Observation resource) {
        AtemfrequenzObservation observation = new AtemfrequenzObservation();
        if(resource.hasDataAbsentReason()){
            observation.setMesswertNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            mapMagnitude(resource).ifPresent(observation::setMesswertMagnitude);
            mapUnit(resource).ifPresent(observation::setMesswertUnits);
        }
        return observation;
    }

    private Optional<String> mapUnit(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getCode());
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
