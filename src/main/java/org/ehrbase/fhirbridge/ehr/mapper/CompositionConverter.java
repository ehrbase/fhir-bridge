package org.ehrbase.fhirbridge.ehr.mapper;

public interface CompositionConverter<X, Y> {

    Y fromComposition(X composition);

    X toComposition(Y resource);
}
