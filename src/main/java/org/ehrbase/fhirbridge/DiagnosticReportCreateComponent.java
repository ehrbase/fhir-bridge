package org.ehrbase.fhirbridge;

import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

public class DiagnosticReportCreateComponent extends CustomFhirComponent<FhirAuditDataset> {

    public DiagnosticReportCreateComponent() {
        super(new DiagnosticReportCreateTransactionConfiguration());
    }
}
