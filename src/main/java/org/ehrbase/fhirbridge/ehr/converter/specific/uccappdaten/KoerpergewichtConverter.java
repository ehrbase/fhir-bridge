package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;

public class KoerpergewichtConverter extends ObservationToObservationConverter<KoerpergewichtObservation> {
    //TODO sadly duplicated mapping opt generated templates changed therefore cannot reuse :(

    @Override
    protected KoerpergewichtObservation convertInternal(Observation observation) {
        KoerpergewichtObservation bodyWeightObservation = new KoerpergewichtObservation();
        bodyWeightObservation.setGewichtMagnitude(mapWeightMagnitude(observation));
        bodyWeightObservation.setGewichtUnits(mapWeightUnit(observation));
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