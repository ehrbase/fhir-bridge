package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @param <C> openEHR Composition type
 * @since 1.0.0
 */
public abstract class ObservationToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Observation, C> {

    @Override
    public C convert(@NonNull Observation resource) {
        C composition = super.convert(resource);

        // Mandatory
        composition.setStartTimeValue(getStartTime(resource)); // StartTimeValue
        composition.setComposer(getComposerOrDefault(resource)); // Composer

        // Optional
        getEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue

        return composition;
    }

    protected ZonedDateTime getStartTime(Observation resource) {
        if (resource.hasEffectiveDateTimeType()) { // EffectiveDateTime
            return resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (resource.hasEffectivePeriod() && resource.getEffectivePeriod().hasStart()) { // EffectivePeriod
            return resource.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else if (resource.hasEffectiveTiming()) { // EffectiveTiming
            return resource.getEffectiveTiming().getEvent()
                    .stream()
                    .map(dateTime -> dateTime.getValueAsCalendar().toZonedDateTime())
                    .findFirst()
                    .orElse(ZonedDateTime.now());
        } else if (resource.hasEffectiveInstantType()) { // EffectiveInstant
            return resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else {
            throw new ConversionException("Start time is not defined in resource");
        }
    }

    protected Optional<ZonedDateTime> getEndTime(Observation resource) {
        if (resource.hasEffectivePeriod() && resource.getEffectivePeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(resource.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    protected PartyProxy getComposerOrDefault(Observation resource) {
        return resource.getPerformer()
                .stream()
                .map(reference -> {
                    PartyIdentified composer = new PartyIdentified();
                    DvIdentifier identifier = new DvIdentifier();
                    if (reference.hasReference()) {
                        identifier.setId(reference.getReference());
                    } else if (reference.hasIdentifier()) {
                        identifier.setAssigner(reference.getIdentifier().getSystem());
                        identifier.setId(reference.getIdentifier().getValue());
                    }
                    composer.addIdentifier(identifier);
                    return (PartyProxy) composer;
                })
                .findFirst()
                .orElse(new PartySelf());
    }
}
