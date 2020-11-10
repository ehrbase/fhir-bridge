package org.ehrbase.fhirbridge.camel.component.fhir.procedure;

import org.ehrbase.fhirbridge.fhir.procedure.ReadProcedureTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class ReadProcedureComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public ReadProcedureComponent() {
        super(new ReadProcedureTransaction());
    }
}
