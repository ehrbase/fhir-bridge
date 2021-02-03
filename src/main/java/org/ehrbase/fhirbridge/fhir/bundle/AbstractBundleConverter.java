package org.ehrbase.fhirbridge.fhir.bundle;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.ehrbase.fhirbridge.fhir.util.FhirUtils;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.core.convert.converter.Converter;

public abstract class AbstractBundleConverter<T> implements Converter<Bundle, T> {

    @SuppressWarnings("unchecked")
    protected T extractRoot(Bundle bundle, Profile profile) {
        return (T) bundle.getEntry().stream()
                .map(Bundle.BundleEntryComponent::getResource)
                .filter(resource -> FhirUtils.hasProfile(resource, profile))
                .findFirst()
                .orElseThrow(); // TODO
    }
}
