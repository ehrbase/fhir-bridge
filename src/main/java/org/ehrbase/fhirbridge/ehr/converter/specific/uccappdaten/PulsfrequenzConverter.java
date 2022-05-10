package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.PulsfrequenzHerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

public class PulsfrequenzConverter extends ObservationToObservationConverter<PulsfrequenzHerzfrequenzObservation> {
    @Override
    protected PulsfrequenzHerzfrequenzObservation convertInternal(Observation observation) {
        PulsfrequenzHerzfrequenzObservation pulseHeartBeatObservation = new PulsfrequenzHerzfrequenzObservation();
        pulseHeartBeatObservation.setFrequenzMagnitude(mapPulseRateMagnitude(observation));;
        pulseHeartBeatObservation.setFrequenzUnits("/min");;
        return pulseHeartBeatObservation;
    }

    private Double mapPulseRateMagnitude(Observation observation) {
        return observation.getComponent().get(0).getValueQuantity().getValue().doubleValue();//Can contain only one component.
    }


}
