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
        for(Observation.ObservationComponentComponent component: observation.getComponent()){
            if(component.hasValueQuantity() && component.hasCode()){
                pulsfrequenzHerzfrequenzJedesEreignisPointEvent.setFrequenzMagnitude(component.getValueQuantity().getValue().doubleValue());
                pulsfrequenzHerzfrequenzJedesEreignisPointEvent.setFrequenzUnits("/min");
            }else{
                throw new ConversionException("ValueQuantity is missing!");
            }
        }

    }

}
