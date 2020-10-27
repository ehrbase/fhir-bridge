package org.ehrbase.fhirbridge;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;

public class DiagnosticReportCreateTransactionConfiguration extends FhirTransactionConfiguration<FhirAuditDataset> {

    public DiagnosticReportCreateTransactionConfiguration() {
        super("diagnostic-report-create",
                "Create Diagnostic Report",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new DiagnosticReportCreateResourceProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
