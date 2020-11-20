package org.ehrbase.fhirbridge.camel.component.ehr.composition;

import org.ehrbase.fhirbridge.ehr.Composition;

public interface CompositionConverter<C extends Composition, T> {

    T fromComposition(C composition);

    C toComposition(T object);
}
