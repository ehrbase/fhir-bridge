package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.instance.model.api.IIdType;
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
    void findDiagnosticReportRead() throws IOException {
        MethodOutcome outcome = create("DiagnosticReport/transactions/provide-diagnostic-report-create.json");
        IIdType id = outcome.getId();

        DiagnosticReport diagnosticReport = read(id.getIdPart(), DiagnosticReport.class);

        Assertions.assertNotNull(diagnosticReport);
        Assertions.assertNotNull(diagnosticReport.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, diagnosticReport.getSubject().getIdentifier().getValue());
    }

    @Test
    void findDiagnosticReportVRead() throws IOException {
        MethodOutcome outcome = create("DiagnosticReport/transactions/provide-diagnostic-report-create.json");
        IIdType id = outcome.getId();

        DiagnosticReport diagnosticReport = vread(id.getIdPart(), id.getVersionIdPart(), DiagnosticReport.class);
        Assertions.assertNotNull(diagnosticReport);
        Assertions.assertNotNull(diagnosticReport.getId(), id.getIdPart());
        Assertions.assertNotNull(diagnosticReport.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, diagnosticReport.getSubject().getIdentifier().getValue());
    }

    @Test
    void findDiagnosticReportSearch() throws IOException {
        for (int i = 0; i < 3; i++) {
            create("DiagnosticReport/transactions/find-diagnostic-report-search.json");
        }

        Bundle bundle = search("DiagnosticReport?subject.identifier=" + PATIENT_ID + "&status=registered");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            DiagnosticReport diagnosticReport = (DiagnosticReport) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, diagnosticReport.getSubject().getIdentifier().getValue());
            Assertions.assertEquals(DiagnosticReport.DiagnosticReportStatus.REGISTERED, diagnosticReport.getStatus());
        });
    }
}
