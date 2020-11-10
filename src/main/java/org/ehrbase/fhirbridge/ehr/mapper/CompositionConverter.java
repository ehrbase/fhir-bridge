package org.ehrbase.fhirbridge.ehr.mapper;

public interface CompositionConverter<X, Y> {

    Y convertFrom(X composition);

    X convertTo(Y resource);
}
