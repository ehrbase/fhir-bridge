package org.ehrbase.fhirbridge.camel.component.ehr.composition;

import org.ehrbase.fhirbridge.ehr.Composition;

public interface CompositionConverter<C extends Composition, T> {

    C toComposition(T object) throws CompositionConversionException;
}
