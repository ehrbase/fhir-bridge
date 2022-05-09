package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzMomentaneHerzfrequenzPointEvent;
import org.hl7.fhir.r4.model.Observation;

public class MomentaneHerzfrequenzConverter extends ObservationToPointEventConverter<PulsfrequenzHerzfrequenzMomentaneHerzfrequenzPointEvent> {

    @Override
    protected PulsfrequenzHerzfrequenzMomentaneHerzfrequenzPointEvent convertInternal(Observation observation) {
        PulsfrequenzHerzfrequenzMomentaneHerzfrequenzPointEvent momentaneHerzfrequenzPointEvent = new PulsfrequenzHerzfrequenzMomentaneHerzfrequenzPointEvent();
        mapMagnitudeAndUnit(momentaneHerzfrequenzPointEvent, observation);
        return momentaneHerzfrequenzPointEvent;
    }


    private void mapMagnitudeAndUnit(PulsfrequenzHerzfrequenzMomentaneHerzfrequenzPointEvent momentaneHerzfrequenzPointEvent, Observation observation) {
        for(Observation.ObservationComponentComponent component: observation.getComponent()){
            if(component.hasValueQuantity() && component.hasCode()){
                momentaneHerzfrequenzPointEvent.setFrequenzMagnitude(component.getValueQuantity().getValue().doubleValue());
                momentaneHerzfrequenzPointEvent.setFrequenzUnits("/min");
            }else{
                throw new ConversionException("ValueQuantity is missing!");
            }
        }

    }

}
