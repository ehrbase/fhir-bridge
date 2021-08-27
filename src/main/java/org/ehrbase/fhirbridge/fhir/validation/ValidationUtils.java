package org.ehrbase.fhirbridge.fhir.validation;

import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.springframework.util.Assert;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static void addInfo(OperationOutcome outcome, String diagnostics, String location) {
        addIssue(outcome, IssueSeverity.WARNING, diagnostics, location);
    }

    public static void addWarning(OperationOutcome outcome, String diagnostics, String location) {
        addIssue(outcome, IssueSeverity.WARNING, diagnostics, location);
    }

    public static void addError(OperationOutcome outcome, String diagnostics, String location) {
        addIssue(outcome, IssueSeverity.ERROR, diagnostics, location);
    }

    public static void addIssue(OperationOutcome outcome, IssueSeverity severity, String diagnostics, String location) {
        Assert.notNull(outcome, "OperationOutcome must not be null");
        Assert.notNull(outcome, "IssueSeverity must not be null");

        var issue = new OperationOutcomeIssueComponent()
                .setSeverity(severity)
                .setCode(OperationOutcome.IssueType.PROCESSING)
                .setDiagnostics(diagnostics);

        if (location != null) {
            issue.addLocation(location);
        }

        outcome.addIssue(issue);
    }
}
