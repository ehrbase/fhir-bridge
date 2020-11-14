package org.ehrbase.fhirbridge.ehr.mapper;

import org.hl7.fhir.instance.model.api.IBaseResource;

public interface CompositionConverter<C, R extends IBaseResource> {

    R fromComposition(C composition);

    C toComposition(R resource);
}
