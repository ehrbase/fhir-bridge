package org.ehrbase.fhirbridge.camel;

import org.ehrbase.fhirbridge.fhir.DiagnosticReportReadTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class DiagnosticReportReadComponent extends CustomFhirComponent<FhirAuditDataset> {

    public DiagnosticReportReadComponent() {
        super(new DiagnosticReportReadTransaction());
    }
}
