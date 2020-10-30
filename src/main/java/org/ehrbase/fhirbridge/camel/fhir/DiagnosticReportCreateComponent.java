package org.ehrbase.fhirbridge.camel.fhir;

import org.ehrbase.fhirbridge.fhir.DiagnosticReportCreateTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class DiagnosticReportCreateComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public DiagnosticReportCreateComponent() {
        super(new DiagnosticReportCreateTransaction());
    }
}
