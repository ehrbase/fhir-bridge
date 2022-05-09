package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.PulseHeartBeatObservation;
import org.hl7.fhir.r4.model.Observation;

public class PulseHeartBeatConverter extends ObservationToObservationConverter<PulseHeartBeatObservation> {
    @Override
    protected PulseHeartBeatObservation convertInternal(Observation observation) {
        PulseHeartBeatObservation pulseHeartBeatObservation = new PulseHeartBeatObservation();
        pulseHeartBeatObservation.setRateMagnitude(mapPulseRateMagnitude(observation));;
        pulseHeartBeatObservation.setRateUnits("/min");;
        return pulseHeartBeatObservation;
    }

    private Double mapPulseRateMagnitude(Observation observation) {
        return observation.getComponent().get(0).getValueQuantity().getValue().doubleValue();//Can contain only one component.
    }


}
