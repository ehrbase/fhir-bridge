package org.ehrbase.fhirbridge.ehr.converter.sofascore;

import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class SofaScoreCompositionConverter extends CompositionConverter<Observation, SOFAComposition> {

    @Override
    public SOFAComposition convertInternal(@NonNull Observation resource) {

        SOFAComposition composition = new SOFAComposition();
        composition.setSofaScore(new SofaScoreObservationConverter().convert(resource));

        // ======================================================================================
        // Required fields by API
        mapTimeDate(resource, composition);

        return composition;
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
