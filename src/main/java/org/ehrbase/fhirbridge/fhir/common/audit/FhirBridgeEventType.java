package org.ehrbase.fhirbridge.fhir.common.audit;

import org.openehealth.ipf.commons.audit.types.EnumeratedCodedValue;
import org.openehealth.ipf.commons.audit.types.EventType;

@SuppressWarnings("java:S115")
public enum FhirBridgeEventType implements EventType, EnumeratedCodedValue<EventType> {

    FindAuditEvent("fhir-find-audit-event", "Find Audit Event"),

    CreateCondition("fhir-create-condition", "Create Condition"),

    FindCondition("fhir-find-condition", "Find Condition"),

    CreateDiagnosticReport("fhir-create-diagnostic-report", "Create Diagnostic Report"),

    FindDiagnosticReport("fhir-find-diagnostic-report", "Find Diagnostic Report"),

    CreateObservation("observation-create", "Create Observation"),

    FindObservation("observation-find", "Find Observation"),

    CreatePatient("patient-create", "Create Patient"),

    FindPatient("patient-find", "Find Patient"),

    CreateProcedure("procedure-create", "Create Procedure"),

    FindProcedure("procedure-find", "Find Procedure");

    private final EventType value;

    FhirBridgeEventType(String code, String displayName) {
        this.value = EventType.of(code, "FHIR Bridge Transactions", displayName);
    }

    @Override
    public EventType getValue() {
        return value;
    }
}
