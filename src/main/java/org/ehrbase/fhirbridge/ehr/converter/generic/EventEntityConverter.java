package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EventEntity;
import org.ehrbase.fhirbridge.ehr.converter.Converter;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.lang.NonNull;

import java.time.temporal.TemporalAccessor;

public abstract class EventEntityConverter <R extends Resource, E extends EventEntity> implements Converter<R, E> {
    @Override
    public E convert(@NonNull R resource) {
        return convertInternal(resource);
    }

    protected abstract E convertInternal(R resource);
}
