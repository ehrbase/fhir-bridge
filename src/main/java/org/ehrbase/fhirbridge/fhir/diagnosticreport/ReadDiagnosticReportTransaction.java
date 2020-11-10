package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class ReadDiagnosticReportTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ReadDiagnosticReportTransaction() {
        super("diag-rep-read",
                "Read Diagnostic Report",
                true,
                null,
                null,
                FhirVersionEnum.R4,
                new ReadDiagnosticReportProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
