package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class TimeConverter {

    static TemporalAccessor convertQuestionnaireResponseTime(QuestionnaireResponse questionnaireResponse) {
        return OffsetDateTime.from(questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime());
    }

    public static TemporalAccessor convertObservationTime(Observation observation) {
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

    static Optional<TemporalAccessor> convertObservationEndTime(Observation observation) {
        if (observation.hasEffectivePeriod() && observation.getEffectivePeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(observation.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    public static TemporalAccessor convertConditionTime(Condition condition) {
        if (condition.hasRecordedDateElement()) {
            return condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime();
        } else if (condition.hasOnset() && condition.hasOnsetDateTimeType()) {
            return condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (condition.hasOnset() && condition.hasOnsetPeriod()) {
            return condition.getOnsetPeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else {
            throw new ConversionException("Start time is not defined in condition");
        }
    }

    public static Optional<TemporalAccessor> convertConditionEndTime(Condition condition) {
        if (condition.hasOnsetPeriod() && condition.getOnsetPeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(condition.getOnsetPeriod().getEndElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    public static TemporalAccessor convertDiagnosticReportTime(DiagnosticReport resource) {
        if (resource.hasEffectiveDateTimeType()) { // EffectiveDateTime
            return resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (resource.hasEffectivePeriod() && resource.getEffectivePeriod().hasStart()) { // EffectivePeriod
            return resource.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else {
            throw new ConversionException("Start time is not defined in DiagnosticReport");
        }
    }

    public static Optional<TemporalAccessor> convertDiagnosticReportEndTime(DiagnosticReport resource) {
        if (resource.hasEffectivePeriod() && resource.getEffectivePeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(resource.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    public static TemporalAccessor convertProcedureTime(Procedure resource) {
        if (resource.hasPerformedDateTimeType()) { // EffectiveDateTime
            return resource.getPerformedDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (resource.hasPerformedPeriod() && resource.getPerformedPeriod().hasStart()) { // EffectivePeriod
            return resource.getPerformedPeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else {
            throw new ConversionException("Start time is not defined in Procedure");
        }
    }

    public static Optional<TemporalAccessor> convertProcedureEndTime(Procedure resource) {
        if (resource.hasPerformedPeriod() && resource.getPerformedPeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(resource.getPerformedPeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }
}
