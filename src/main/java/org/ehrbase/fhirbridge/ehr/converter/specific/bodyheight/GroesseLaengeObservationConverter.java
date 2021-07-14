package org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class GroesseLaengeObservationConverter extends ObservationToObservationConverter<GroesseLaengeObservation> {

    @Override
    protected GroesseLaengeObservation convertInternal(Observation resource) {
        GroesseLaengeObservation groesseLaengeObservation = new GroesseLaengeObservation();
        setGroesseLaengeUnits(resource).ifPresent(groesseLaengeObservation::setGroesseLaengeUnits);
        setGroesseLaengeValue(resource).ifPresent(groesseLaengeObservation::setGroesseLaengeMagnitude);
        return groesseLaengeObservation;
    }

    private Optional<Double> setGroesseLaengeValue(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> setGroesseLaengeUnits(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getCode());
        } else {
            return Optional.empty();
        }
    }

}
