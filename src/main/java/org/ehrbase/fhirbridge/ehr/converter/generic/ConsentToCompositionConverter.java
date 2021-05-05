package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Consent;
import org.springframework.lang.NonNull;

import java.time.Instant;

public abstract class ConsentToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Consent, C> {

    @Override
    public C convert(@NonNull Consent resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertConsentTime(resource));
        return composition;
    }
}
