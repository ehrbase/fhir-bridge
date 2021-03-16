package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public abstract class ObservationToPointEventConverter<P extends PointEventEntity> extends EventEntityConverter<Observation, P> {

    @Override
    public P convert(@NonNull Observation observation) {
        P pointEvent = super.convert(observation);
        pointEvent.setTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        return pointEvent;
    }
}
