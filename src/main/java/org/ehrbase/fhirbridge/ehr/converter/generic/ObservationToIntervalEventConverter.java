package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public abstract class ObservationToIntervalEventConverter <P extends IntervalEventEntity> extends EventEntityConverter<Observation, P> {

    @Override
    public P convert(@NonNull Observation observation) {
        P intervalEvent = super.convert(observation);
        if(TimeConverter.convertObservationEndTime(observation).isPresent()){
            intervalEvent.setTimeValue(TimeConverter.convertObservationEndTime(observation).get());
        }else{
            throw new ConversionException("There is no End time for the effective period defined, interval event cant be mapped");
        }
        if(TimeConverter.convertObservationTimeInterval(observation).isPresent()){
            intervalEvent.setWidthValue(TimeConverter.convertObservationTimeInterval(observation).get());
        }else{
            throw new ConversionException("There is no end or start time for the effective period defined, interval event cant be mapped");
        }
        intervalEvent.setMathFunctionDefiningCode(MathFunction.MAXIMUM);
        return intervalEvent;
    }
}
