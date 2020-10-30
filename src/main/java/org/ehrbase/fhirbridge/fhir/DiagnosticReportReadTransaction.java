package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;

public class DiagnosticReportReadTransaction extends FhirTransactionConfiguration<FhirAuditDataset> {

    public DiagnosticReportReadTransaction() {
        super("diagnostic-report-read",
                "Read Diagnostic Report",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new DiagnosticReportReadProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
