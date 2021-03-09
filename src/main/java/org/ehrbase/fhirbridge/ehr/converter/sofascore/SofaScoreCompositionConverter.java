package org.ehrbase.fhirbridge.ehr.converter.sofascore;

import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class SofaScoreCompositionConverter extends AbstractCompositionConverter<Observation, SOFAComposition> {

    @Override
    public SOFAComposition convert(@NonNull Observation observation) {

        SOFAComposition result = new SOFAComposition();
        mapCommonAttributes(observation, result);

        result.setSofaScore(new SofaScoreObservationConverter().convert(observation));

        // ======================================================================================
        // Required fields by API
        mapTimeDate(observation, result);

        return result;
    }

    private void mapTimeDate(Observation observation, SOFAComposition result) {
        tryEffectiveDateTime(observation, result);
        tryEffectiveInstantType(observation, result);
        tryEffectivePeriodType(observation, result);
    }

    private void tryEffectiveDateTime(Observation observation, SOFAComposition result) {
        try {
            result.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        } catch (FHIRException fhirException) {
            if (isTimeTypeException(fhirException.toString())) {
                throw fhirException;
            }
        }
    }

    private void tryEffectiveInstantType(Observation observation, SOFAComposition result) {
        try {
            result.setStartTimeValue(observation.getEffectiveInstantType().getValueAsCalendar().toZonedDateTime());
        } catch (FHIRException fhirException) {
            if (isTimeTypeException(fhirException.toString())) {
                throw fhirException;
            }
        }
    }

    private void tryEffectivePeriodType(Observation observation, SOFAComposition result) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(observation.getEffectivePeriod().getStart().toInstant(), ZoneOffset.UTC);
            result.setStartTimeValue(date);
            result.setEndTimeValue(date);
        } catch (FHIRException fhirException) {
            if (isTimeTypeException(fhirException.toString())) {
                throw fhirException;
            }
        }
    }

    private boolean isTimeTypeException(String exceptionMessage) {
        return !(exceptionMessage.contains("Type mismatch: the type") && exceptionMessage.contains("was expected,") && exceptionMessage.contains("was encountered"));
    }

}
