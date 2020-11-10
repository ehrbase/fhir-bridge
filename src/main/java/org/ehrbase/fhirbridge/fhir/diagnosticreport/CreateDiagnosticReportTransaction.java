package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class CreateDiagnosticReportTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public CreateDiagnosticReportTransaction() {
        super("diag-rep-create",
                "Create DiagnosticReport",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new CreateDiagnosticReportProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
