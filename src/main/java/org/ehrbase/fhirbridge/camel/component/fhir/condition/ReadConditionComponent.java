package org.ehrbase.fhirbridge.camel.component.fhir.condition;

import org.ehrbase.fhirbridge.fhir.condition.ReadConditionTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class ReadConditionComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public ReadConditionComponent() {
        super(new ReadConditionTransaction());
    }
}
