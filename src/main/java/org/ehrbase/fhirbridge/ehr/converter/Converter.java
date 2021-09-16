package org.ehrbase.fhirbridge.ehr.converter;

@FunctionalInterface
public interface Converter<S, T> {

    T convert(S source);
}
