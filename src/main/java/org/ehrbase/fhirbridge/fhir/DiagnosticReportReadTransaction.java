package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class DiagnosticReportReadTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public DiagnosticReportReadTransaction() {
        super("diagnostic-report-read",
                "Read Diagnostic Report",
                false,
                new DiagnosticReportAuditStrategy(false),
                new DiagnosticReportAuditStrategy(true),
                FhirVersionEnum.R4,
                new DiagnosticReportReadProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
