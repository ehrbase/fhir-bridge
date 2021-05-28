package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Find Diagnostic Report" transaction.
 */
class FindDiagnosticReportTransactionIT extends AbstractTransactionIT {

    @Test
    void findDiagnosticReportRead() throws Exception {
        var outcome = create("DiagnosticReport/transactions/provide-diagnostic-report-create.json");
        var id = outcome.getId();

        var diagnosticReport = read(id.getIdPart(), DiagnosticReport.class);

        Assertions.assertNotNull(diagnosticReport);
        Assertions.assertNotNull(diagnosticReport.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, diagnosticReport.getSubject().getIdentifier().getValue());
    }

    @Test
    void findDiagnosticReportVRead() throws Exception {
        var outcome = create("DiagnosticReport/transactions/provide-diagnostic-report-create.json");
        var id = outcome.getId();

        var diagnosticReport = vread(id.getIdPart(), id.getVersionIdPart(), DiagnosticReport.class);
        Assertions.assertNotNull(diagnosticReport);
        Assertions.assertNotNull(diagnosticReport.getId(), id.getIdPart());
        Assertions.assertNotNull(diagnosticReport.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, diagnosticReport.getSubject().getIdentifier().getValue());
    }

    @Test
    void findDiagnosticReportSearch() throws IOException {
        create("DiagnosticReport/transactions/find-diagnostic-report-search.json");
        create("DiagnosticReport/transactions/find-diagnostic-report-search.json");
        create("DiagnosticReport/transactions/find-diagnostic-report-search.json");

        Bundle bundle = search("DiagnosticReport?subject.identifier=" + PATIENT_ID + "&status=registered");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            var diagnosticReport = (DiagnosticReport) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, diagnosticReport.getSubject().getIdentifier().getValue());
            Assertions.assertEquals(DiagnosticReport.DiagnosticReportStatus.REGISTERED, diagnosticReport.getStatus());
        });
    }
}
