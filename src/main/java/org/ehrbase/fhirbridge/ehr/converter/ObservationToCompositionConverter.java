package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartyIdentified;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Calendar;

/**
 * @param <C> openEHR Composition type
 * @since 1.0.0
 */
public abstract class ObservationToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Observation, C> {

    @Override
    public C convert(@NonNull Observation resource) {
        C composition = super.convert(resource);
        if (resource.hasEffectiveDateTimeType()) {
            Calendar effectiveDateTime = resource.getEffectiveDateTimeType().getValueAsCalendar();
            composition.setStartTimeValue(effectiveDateTime.toInstant());
        } else {
            composition.setStartTimeValue(Instant.now());
        }

        composition.setComposer(new PartyIdentified());
        return composition;
    }
}
