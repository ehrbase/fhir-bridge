package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Provide Diagnostic Report" transaction.
 */
class ProvideDiagnosticReportTransactionIT extends AbstractTransactionIT {

    @Test
    void provideDiagnosticReportCreate() throws IOException {
        var outcome = create("DiagnosticReport/transactions/provide-diagnostic-report-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void provideDiagnosticReportConditionalUpdate() throws IOException {
        MethodOutcome outcome;

        outcome = create("DiagnosticReport/transactions/provide-diagnostic-report-create.json");
        var id = outcome.getId();

        outcome = update("DiagnosticReport/transactions/provide-diagnostic-report-update.json", "DiagnosticReport?_id=" + id.getIdPart() + "&subject.identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        var diagnosticReport = (DiagnosticReport) outcome.getResource();

        Assertions.assertEquals(PATIENT_ID, diagnosticReport.getSubject().getIdentifier().getValue());
        Assertions.assertEquals("http://external.fhir.server/ServiceRequest/987", diagnosticReport.getBasedOn().get(0).getReference());
        Assertions.assertEquals(DiagnosticReport.DiagnosticReportStatus.CORRECTED, diagnosticReport.getStatus());
    }
}
