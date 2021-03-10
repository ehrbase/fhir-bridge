package org.ehrbase.fhirbridge.fhir.common.audit;

import org.openehealth.ipf.commons.audit.types.EnumeratedCodedValue;
import org.openehealth.ipf.commons.audit.types.EventType;

@SuppressWarnings("java:S115")
public enum FhirBridgeEventType implements EventType, EnumeratedCodedValue<EventType> {

    FindAuditEvent("audit-event-find", "Find Audit Event"),

    CreateCondition("condition-create", "Create Condition"),

    FindCondition("condition-find", "Find Condition"),

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
