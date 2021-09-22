package org.ehrbase.fhirbridge.ehr.converter.specific.bodytemperature;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.definition.KoerpertemperaturObservation;
import org.hl7.fhir.r4.model.Observation;
import java.util.Optional;

public class KoerpertemperaturObservationConverter extends ObservationToObservationConverter<KoerpertemperaturObservation> {

    @Override
    protected KoerpertemperaturObservation convertInternal(Observation resource) {
        KoerpertemperaturObservation koerpertemperaturObservation = new KoerpertemperaturObservation();
        if(resource.hasValueQuantity()) {
            setValue(resource).ifPresent(koerpertemperaturObservation::setTemperaturMagnitude);
            setUnits(resource).ifPresent(koerpertemperaturObservation::setTemperaturUnits);
        }else{
            koerpertemperaturObservation.setTemperaturNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return koerpertemperaturObservation;
    }

    private Optional<Double> setValue(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> setUnits(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getCode());
        } else {
            return Optional.empty();
        }
    }
}
