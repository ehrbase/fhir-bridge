package org.ehrbase.fhirbridge.camel.component.fhir.diagnosticreport;

import org.ehrbase.fhirbridge.fhir.diagnosticreport.ReadDiagnosticReportTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class ReadDiagnosticReportComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public ReadDiagnosticReportComponent() {
        super(new ReadDiagnosticReportTransaction());
    }
}
