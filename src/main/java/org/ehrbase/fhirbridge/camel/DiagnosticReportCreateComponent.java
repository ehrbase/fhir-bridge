package org.ehrbase.fhirbridge.camel;

import org.ehrbase.fhirbridge.fhir.DiagnosticReportCreateTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class DiagnosticReportCreateComponent extends CustomFhirComponent<FhirAuditDataset> {

    public DiagnosticReportCreateComponent() {
        super(new DiagnosticReportCreateTransaction());
    }
}
