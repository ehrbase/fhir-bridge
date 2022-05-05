package org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzJedesEreignisPointEvent;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class HerzfrequenzJedesEreignisPointEventConverter extends ObservationToPointEventConverter<PulsfrequenzHerzfrequenzJedesEreignisPointEvent> {

    @Override
    protected PulsfrequenzHerzfrequenzJedesEreignisPointEvent convertInternal(Observation observation) {
        PulsfrequenzHerzfrequenzJedesEreignisPointEvent pulsfrequenzHerzfrequenzJedesEreignisPointEvent = new PulsfrequenzHerzfrequenzJedesEreignisPointEvent();
        mapMagnitudeAndUnit(pulsfrequenzHerzfrequenzJedesEreignisPointEvent, observation);
        return pulsfrequenzHerzfrequenzJedesEreignisPointEvent;
    }


    private void mapMagnitudeAndUnit(PulsfrequenzHerzfrequenzJedesEreignisPointEvent pulsfrequenzHerzfrequenzJedesEreignisPointEvent, Observation observation) {
        if(observation.hasValueQuantity()){
            getValue(observation).ifPresent(pulsfrequenzHerzfrequenzJedesEreignisPointEvent::setFrequenzMagnitude);
            getUnit(observation).ifPresent(pulsfrequenzHerzfrequenzJedesEreignisPointEvent::setFrequenzUnits);
        }else{
            throw new ConversionException("ValueQuantity is missing!");
        }
    }

    private Optional<Double> getValue(Observation observation) {
        if(observation.hasValueQuantity() ){
            return Optional.of(observation.getValueQuantity().getValue().doubleValue());
        }else{
            return Optional.empty();
        }
    }

    private Optional<String> getUnit(Observation observation) {
        if(observation.hasValueQuantity() ){
            return Optional.of(observation.getValueQuantity().getCode());
        }else{
            return Optional.empty();
        }
    }

}
