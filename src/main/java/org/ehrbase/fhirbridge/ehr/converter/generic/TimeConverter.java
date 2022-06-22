package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Consent;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Immunization;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Specimen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class TimeConverter {
    private static final Logger LOG = LoggerFactory.getLogger(TimeConverter.class);

    private TimeConverter() {
        throw new IllegalStateException("Utility class");
    }

    static TemporalAccessor convertQuestionnaireResponseTime(QuestionnaireResponse questionnaireResponse) {
        return OffsetDateTime.from(questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime());
    }

    public static TemporalAccessor convertObservationTime(Observation observation) {
        if (observation.hasEffectiveDateTimeType() && !observation.getEffectiveDateTimeType().hasExtension()) {
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
            return observation.getEffectiveInstantType().getValueAsCalendar().toZonedDateTime();
        } else {
            return ZonedDateTime.now();
        }
    }

    static Optional<TemporalAccessor> convertObservationEndTime(Observation observation) {
        if (observation.hasEffectivePeriod() && observation.getEffectivePeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(observation.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    public static TemporalAccessor convertCompositionTime(Composition composition) {
        if (composition.getDate() != null) {
            return composition.getDateElement().getValueAsCalendar().toZonedDateTime();
        } else {
            return ZonedDateTime.now();
        }
    }

    public static TemporalAccessor convertConditionTime(Condition condition) {
        if (condition.hasRecordedDateElement()) {
            return condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime();
        } else {
            return convertConditionOnset(condition);
        }
    }

    public static TemporalAccessor convertConditionOnset(Condition condition) {
        if (condition.hasOnset() && condition.hasOnsetDateTimeType()) {
            return condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (condition.hasOnset() && condition.hasOnsetPeriod()) {
            return condition.getOnsetPeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else {
            return ZonedDateTime.now();
        }
    }

    public static Optional<TemporalAccessor> convertConditionAbatementTime(Condition condition) {
        if (condition.hasAbatementDateTimeType()) {
            return Optional.of(condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());
        } else if (condition.hasAbatementPeriod() && condition.getAbatementPeriod().hasStart()) {
            return Optional.of(condition.getAbatementPeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else if (condition.hasAbatementAge()) {
            return parseConditionAge(condition);
        } else if (condition.hasAbatementRange()) {
            LOG.warn("A range is not a supported date for an abatement, the FHIR-bridge does not support such imprecise values, therefore it is not mapped. Please use DateTimeType instead.");
            return Optional.empty();
        } else if (condition.hasAbatementStringType()) {
            LOG.warn("A String is not a supported date for an abatement, the FHIR-bridge does not support such imprecise values, therefore it is not mapped. Please use DateTimeType instead.");
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    private static Optional<TemporalAccessor> parseConditionAge(Condition condition) {
        try {
            double yearToday = ZonedDateTime.now().getYear();
            double age = condition.getAbatementAge().getValue().doubleValue();
            double abatement = yearToday - age;
            return Optional.of(ZonedDateTime.now().toLocalDateTime().withYear((int) abatement));
        } catch (Exception exception) {
            LOG.warn("The mapping of Age failed, instead nothing is used for abatement, for precise mappings please use a real date time the DateTimeType");
            return Optional.empty();
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
            return ZonedDateTime.now();
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
        if (resource.hasPerformedDateTimeType() && resource.getPerformedDateTimeType().getExtension().isEmpty()) { // EffectiveDateTime
            return resource.getPerformedDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (resource.hasPerformedPeriod() && resource.getPerformedPeriod().hasStart()) { // EffectivePeriod
            return resource.getPerformedPeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else {
            return ZonedDateTime.now();
        }
    }

    public static Optional<TemporalAccessor> convertProcedureEndTime(Procedure resource) {
        if (resource.hasPerformedPeriod() && resource.getPerformedPeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(resource.getPerformedPeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    public static TemporalAccessor convertImmunizationTime(Immunization immunization) {
        if (immunization.hasOccurrenceDateTimeType() && !immunization.getOccurrenceDateTimeType().hasExtension()) {
            return immunization.getOccurrenceDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else {
            LOG.warn("No occurrence Date Time was given, as default the current time is now taken. This date time should better be added to the resource");
            return ZonedDateTime.now();
        }
    }

    public static TemporalAccessor convertMedicationStatmentTime(MedicationStatement medicationStatement) {
        if (medicationStatement.hasEffectiveDateTimeType()) { // EffectiveDateTime
            return medicationStatement.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (medicationStatement.hasEffectivePeriod() && medicationStatement.getEffectivePeriod().hasStart()) { // EffectivePeriod
            return medicationStatement.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime();
        } else {
            return ZonedDateTime.now();
        }
    }

    public static Optional<TemporalAccessor> convertMedicationStatementEndTime(MedicationStatement medicationStatement) {
        if (medicationStatement.hasEffectivePeriod() && medicationStatement.getEffectivePeriod().hasEnd()) { // EffectivePeriod
            return Optional.of(medicationStatement.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    public static TemporalAccessor convertConsentTime(Consent resource) {
        if (resource.hasDateTime()) {
            return resource.getDateTimeElement().getValueAsCalendar().toZonedDateTime();
        } else {
            return OffsetDateTime.now();
        }
    }

    public static TemporalAccessor convertPatientDateTime(Patient patient) {
        if(patient.hasExtension("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age")){
            return convertAgeExtensionTime(patient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age"));
        }else{
            LOG.info("No age extension was contained in Patient, therefore date time NOW is used");
            return OffsetDateTime.now();
        }
    }

    public static TemporalAccessor convertAgeExtensionTime(Extension extension) {
        if (extension == null) {
            return OffsetDateTime.now();
        }
        Extension dataTimeOfDocumentationExtension = extension.getExtensionByUrl("dateTimeOfDocumentation");
        if (dataTimeOfDocumentationExtension == null || dataTimeOfDocumentationExtension.getValue().hasExtension("http://hl7.org/fhir/StructureDefinition/data-absent-reason")){
            return OffsetDateTime.now();
        }
        DateTimeType dateTimeOfDocumentationDt = (DateTimeType) dataTimeOfDocumentationExtension.getValue();
        return dateTimeOfDocumentationDt.getValueAsCalendar().toZonedDateTime();
    }

    public static TemporalAccessor convertEncounterTime(Encounter encounter) {
        return OffsetDateTime.from(encounter.getPeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
    }

    public static Optional<TemporalAccessor> convertEncounterEndTime(Encounter encounter) {
        if (encounter.getPeriod().hasEndElement()) {
            return Optional.of(OffsetDateTime.from(encounter.getPeriod().getEndElement().getValueAsCalendar().toZonedDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<TemporalAccessor> convertEncounterLocationTime(Encounter.EncounterLocationComponent location) {

        if (location.hasPeriod()) {
            return Optional.of(OffsetDateTime.from(location.getPeriod().getStartElement().getValueAsCalendar().toZonedDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<TemporalAccessor> convertEncounterLocationEndTime(Encounter.EncounterLocationComponent location) {

        if (location.hasPeriod() && location.getPeriod().hasEndElement()) {
            return Optional.of(OffsetDateTime.from(location.getPeriod().getEndElement().getValueAsCalendar().toZonedDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<TemporalAccessor> convertSpecimanCollection(Specimen.SpecimenCollectionComponent collection) {
        if (collection.hasCollectedDateTimeType()) {
            return Optional.of(collection.getCollectedDateTimeType().getValueAsCalendar().toZonedDateTime());
        } else if (collection.hasCollectedPeriod() && collection.getCollectedPeriod().hasStart()) {
            return Optional.of(collection.getCollectedPeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else if (collection.hasCollectedPeriod() && collection.getCollectedPeriod().hasEnd()) {
            return Optional.of(collection.getCollectedPeriod().getEndElement().getValueAsCalendar().toZonedDateTime());
        }
        return Optional.empty();
    }

    public static Optional<Duration> convertObservationTimeInterval(Observation observation) {
        if(observation.getEffectivePeriod().hasStart() && observation.getEffectivePeriod().hasEnd()){
            return Optional.of(Duration.between(observation.getEffectivePeriod().getStartElement().getValueAsCalendar().toZonedDateTime(),
                    observation.getEffectivePeriod().getEndElement().getValueAsCalendar().toZonedDateTime()));
        }else{
            return Optional.empty();
        }
    }
}
