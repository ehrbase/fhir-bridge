package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EventEntity;
import org.ehrbase.fhirbridge.ehr.converter.ResourceConverter;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.lang.NonNull;

public abstract class EventEntityConverter<R extends Resource, E extends EventEntity> implements ResourceConverter<R, E> {
    @Override
    public E convert(@NonNull R resource) {
        return convertInternal(resource);
    }

    protected abstract E convertInternal(R resource);
}
