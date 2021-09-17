package org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class GroesseLaengeObservationConverter extends ObservationToObservationConverter<GroesseLaengeObservation> {

    @Override
    protected GroesseLaengeObservation convertInternal(Observation resource) {
        GroesseLaengeObservation groesseLaengeObservation = new GroesseLaengeObservation();
        if (resource.hasValueQuantity()) {
            mapGroesseLaengeUnit(resource).ifPresent(groesseLaengeObservation::setGroesseLaengeUnits);
            mapGroesseLaengeMagnitude(resource).ifPresent(groesseLaengeObservation::setGroesseLaengeMagnitude);
        } else {
            groesseLaengeObservation.setGroesseLaengeNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return groesseLaengeObservation;
    }

    private Optional<Double> mapGroesseLaengeMagnitude(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapGroesseLaengeUnit(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(resource.getValueQuantity().getCode());
        } else {
            return Optional.empty();
        }
    }

}
