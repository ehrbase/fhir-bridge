package org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;
import java.util.Optional;

public class KoerpergewichtObservationConverter extends ObservationToObservationConverter<KoerpergewichtObservation> {
    @Override
    protected KoerpergewichtObservation convertInternal(Observation resource) {
        KoerpergewichtObservation observation = new KoerpergewichtObservation();
        mapGewichtMagnitude(resource).ifPresent(observation::setGewichtMagnitude);
        mapGewichtUnit(resource).ifPresent(observation::setGewichtUnits);
        return observation;
    }

    private Optional<String> mapGewichtUnit(Observation resource) {
        if(resource.hasValueQuantity()){
            return Optional.of(resource.getValueQuantity().getCode());
        }else{
            return Optional.empty();
        }
    }

    private Optional<Double> mapGewichtMagnitude(Observation resource) {
        if(resource.hasValueQuantity() ){
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        }else{
            return Optional.empty();
        }

    }
}
