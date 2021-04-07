package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.hl7.fhir.r4.model.Element;
import org.springframework.lang.NonNull;

import java.time.temporal.TemporalAccessor;

@FunctionalInterface
public interface ElementConverter<E extends Element, T> {

    T convert(@NonNull E element, Language language, TemporalAccessor authored);
}
