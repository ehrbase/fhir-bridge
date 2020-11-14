package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.fhirbridge.ehr.Composition;

public interface CompositionConverter {

    Object fromComposition(Composition composition);

    Composition toComposition(Object object);
}
