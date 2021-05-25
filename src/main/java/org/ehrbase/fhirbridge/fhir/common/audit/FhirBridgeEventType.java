package org.ehrbase.fhirbridge.fhir.common.audit;

import org.openehealth.ipf.commons.audit.types.EnumeratedCodedValue;
import org.openehealth.ipf.commons.audit.types.EventType;

@SuppressWarnings("java:S115")
public enum FhirBridgeEventType implements EventType, EnumeratedCodedValue<EventType> {

    // Condition

    PROVIDE_CONDITION("condition-provide", "Provide Condition"),

    FIND_CONDITION("condition-find", "Find Condition"),

    // Consent

    PROVIDE_CONSENT("consent-provide", "Provide Consent"),

    FIND_CONSENT("consent-find", "Find Consent"),

    // MedicationStatement

    PROVIDE_MEDICATION_STATEMENT("medication-statement-provide", "Provide Medication Statement"),

    FIND_MEDICATION_STATEMENT("medication-statement-find", "Find Medication Statement"),

    // Patient

    PROVIDE_PATIENT("patient-provide", "Provide Patient"),

    FIND_PATIENT("patient-find", "Find Patient"),

    // Procedure

    PROVIDE_PROCEDURE("procedure-provide", "Provide Procedure"),

    FIND_PROCEDURE("procedure-find", "Find Procedure"),

    // Questionnaire-Response

    PROVIDE_QUESTIONNAIRE_RESPONSE("questionnaire-response-provide", "Provide Questionnaire Response"),

    FIND_QUESTIONNAIRE_RESPONSE("questionnaire-response-find", "Find Questionnaire Response"),

    // Review

    FindAuditEvent("audit-event-find", "Find Audit Event"),

    CreateDiagnosticReport("diagnostic-report-create", "Create Diagnostic Report"),

    FindDiagnosticReport("diagnostic-report-find", "Find Diagnostic Report"),

    CreateObservation("observation-create", "Create Observation"),

    FindObservation("observation-find", "Find Observation");

    private final EventType value;

    FhirBridgeEventType(String code, String displayName) {
        this.value = EventType.of(code, "FHIR Bridge Transactions", displayName);
    }

    @Override
    public EventType getValue() {
        return value;
    }
}
