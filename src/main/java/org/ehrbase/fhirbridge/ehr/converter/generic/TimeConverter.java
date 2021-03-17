package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

class TimeConverter {

    static TemporalAccessor convertObservationTime(Observation observation){
        if (observation.hasEffectiveDateTimeType()) { // EffectiveDateTime
            return observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (observation.hasEffectivePeriod() && observation.getEffectivePeriod().hasStart()) { // EffectivePeriod
            return observation.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else if (observation.hasEffectiveTiming()) { // EffectiveTiming
            return observation.getEffectiveTiming().getEvent()
                    .stream()
                    .map(dateTime -> dateTime.getValueAsCalendar().toZonedDateTime())
                    .findFirst()
                    .orElse(ZonedDateTime.now());
        } else if (observation.hasEffectiveInstantType()) { // EffectiveInstant
            return observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else {
            throw new ConversionException("Start time is not defined in observation");
        }
    }

    static Optional<TemporalAccessor> convertObservationEndTime(Observation observation){
            if (observation.hasEffectivePeriod() && observation.getEffectivePeriod().hasEnd()) { // EffectivePeriod
                return Optional.of(observation.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
            } else {
                return Optional.empty();
        }
    }
}
