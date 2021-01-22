package org.ehrbase.fhirbridge.comparators;

import org.javers.core.diff.custom.CustomValueComparator;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;

public class CustomTemporalAcessorComparator  implements CustomValueComparator<TemporalAccessor> {

    @Override
    public boolean equals(TemporalAccessor ehrbaseCompositionDate, TemporalAccessor mappedCompositionDate) {
        LocalDateTime parsedEhrbaseDate = parseToLocalDateTime(ehrbaseCompositionDate);
        LocalDateTime parsedMappingDate = parseToLocalDateTime(mappedCompositionDate);
        return parsedEhrbaseDate.equals(parsedMappingDate);
    }

    private LocalDateTime parseToLocalDateTime(TemporalAccessor temporalAccessor) {
        if(temporalAccessor.getClass().equals(OffsetDateTime.class)){
            OffsetDateTime offsetDateTime = (OffsetDateTime) temporalAccessor;
            return offsetDateTime.toLocalDateTime();
        }else if(temporalAccessor.getClass().equals(ZonedDateTime.class)){
            ZonedDateTime zonedDateTime = (ZonedDateTime) temporalAccessor;
            return zonedDateTime.toLocalDateTime();
        }
        return (LocalDateTime) temporalAccessor;
    }


    @Override
    public String toString(TemporalAccessor temporalAccessor) {
        return temporalAccessor.toString();
    }
}
