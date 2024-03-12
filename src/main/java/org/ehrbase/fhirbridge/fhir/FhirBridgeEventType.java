package org.ehrbase.fhirbridge.fhir;

import org.openehealth.ipf.commons.audit.types.EnumeratedCodedValue;
import org.openehealth.ipf.commons.audit.types.EventType;

public enum FhirBridgeEventType implements EventType, EnumeratedCodedValue<EventType> {

    // AuditEvent

    FIND_AUDIT_EVENT("audit-event-find", "Find Audit Event"),

    // Condition

    PROVIDE_CONDITION("condition-provide", "Provide Condition"),

    FIND_CONDITION("condition-find", "Find Condition"),

    // Consent

    PROVIDE_CONSENT("consent-provide", "Provide Consent"),

    FIND_CONSENT("consent-find", "Find Consent"),

    // Composition
    PROVIDE_COMPOSITION("composition-provide", "Provide Composition"),

    FIND_COMPOSITION("composition-find","Find Composition"),

    // DiagnosticReport

    PROVIDE_DIAGNOSTIC_REPORT("diagnostic-report-provide", "Provide Diagnostic Report"),

    FIND_DIAGNOSTIC_REPORT("diagnostic-report-find", "Find Diagnostic Report"),

    // Encounter
    PROVIDE_ENCOUNTER("encounter-provide", "Provide Encounter"),

    FIND_ENCOUNTER("encounter-find", "Find Encounter"),

    // MedicationStatement

    PROVIDE_MEDICATION_STATEMENT("medication-statement-provide", "Provide Medication Statement"),

    FIND_MEDICATION_STATEMENT("medication-statement-find", "Find Medication Statement"),

    // Observation

    PROVIDE_OBSERVATION("observation-create", "Provide Observation"),

    FIND_OBSERVATION("observation-find", "Find Observation"),

    // Patient


    PROVIDE_PATIENT("patient-provide", "Provide Patient"),

    FIND_PATIENT("patient-find", "Find Patient"),

    // Procedure

    PROVIDE_PROCEDURE("procedure-provide", "Provide Procedure"),

    FIND_PROCEDURE("procedure-find", "Find Procedure"),

    // Questionnaire-Response

    PROVIDE_QUESTIONNAIRE_RESPONSE("questionnaire-response-provide", "Provide Questionnaire Response"),

    FIND_QUESTIONNAIRE_RESPONSE("questionnaire-response-find", "Find Questionnaire Response"),


    //Specimen
    PROVIDE_SPECIMEN("specimen-provide", "Provide Specimen"),

    FIND_SPECIMEN("specimen-find", "Find Specimen");

    private final EventType value;

    FhirBridgeEventType(String code, String displayName) {
        this.value = EventType.of(code, "FHIR Bridge Transactions", displayName);
    }

    @Override
    public EventType getValue() {
        return value;
    }
}
