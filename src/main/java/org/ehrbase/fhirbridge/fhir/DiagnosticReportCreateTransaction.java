package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class DiagnosticReportCreateTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public DiagnosticReportCreateTransaction() {
        super("diagnostic-report-create",
                "Create Diagnostic Report",
                false,
                new DiagnosticReportAuditStrategy(false),
                new DiagnosticReportAuditStrategy(true),
                FhirVersionEnum.R4,
                new DiagnosticReportCreateProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
