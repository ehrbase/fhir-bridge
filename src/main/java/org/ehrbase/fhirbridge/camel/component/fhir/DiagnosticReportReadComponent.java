package org.ehrbase.fhirbridge.camel.component.fhir;

import org.ehrbase.fhirbridge.fhir.DiagnosticReportReadTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class DiagnosticReportReadComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public DiagnosticReportReadComponent() {
        super(new DiagnosticReportReadTransaction());
    }
}
