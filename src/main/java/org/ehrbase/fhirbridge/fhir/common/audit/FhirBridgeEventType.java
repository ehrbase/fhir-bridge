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

    // Review

    FindAuditEvent("audit-event-find", "Find Audit Event"),

    CreateDiagnosticReport("diagnostic-report-create", "Create Diagnostic Report"),

    FindDiagnosticReport("diagnostic-report-find", "Find Diagnostic Report"),

    CreateObservation("observation-create", "Create Observation"),

    FindObservation("observation-find", "Find Observation"),

    CreatePatient("patient-create", "Create Patient"),

    FindPatient("patient-find", "Find Patient"),

    CreateProcedure("procedure-create", "Create Procedure"),

    FindProcedure("procedure-find", "Find Procedure"),

    CreateQuestionnaireResponse("questionnaire-response-create", "Create Questionnaire Response"),

    FindQuestionnaireResponse("questionnaire-response-find", "Find Questionnaire Response");

    private final EventType value;

    FhirBridgeEventType(String code, String displayName) {
        this.value = EventType.of(code, "FHIR Bridge Transactions", displayName);
    }

    @Override
    public EventType getValue() {
        return value;
    }
}
