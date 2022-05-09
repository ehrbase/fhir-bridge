package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BodyWeightObservation;
import org.hl7.fhir.r4.model.Observation;

public class BodyWeightObservationConverter extends ObservationToObservationConverter<BodyWeightObservation> {
    //TODO sadly duplicated mapping opt generated templates changed therefore cannot reuse :(

    @Override
    protected BodyWeightObservation convertInternal(Observation observation) {
        BodyWeightObservation bodyWeightObservation = new BodyWeightObservation();
        bodyWeightObservation.setWeightMagnitude(mapWeightMagnitude(observation));
        bodyWeightObservation.setWeightUnits(mapWeightUnit(observation));
        mapWeightUnit(observation);
        return bodyWeightObservation;
    }

    private String mapWeightUnit(Observation observation) {
        return observation.getComponent().get(0).getValueQuantity().getUnit(); //Can contain only one component.
    }

    private double mapWeightMagnitude(Observation observation) {
        return observation.getComponent().get(0).getValueQuantity().getValue().doubleValue(); //Can contain only one component.
    }
}