package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.client.classgenerator.interfaces.RMEntity;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.lang.NonNull;

/**
 * @param <S> source type
 * @param <T> target type
 * @since 1.0.0
 */
@FunctionalInterface
public interface Converter<S extends Resource, T> {
    T convert(@NonNull S resource);
}