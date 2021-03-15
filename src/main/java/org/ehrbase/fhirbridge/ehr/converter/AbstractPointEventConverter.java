package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;


public abstract class AbstractPointEventConverter<R extends Resource, E extends PointEventEntity> implements Converter<R, E> {

    protected void mapCommonAttributes(Observation observation, E entry) {
        entry.setTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
    }
}