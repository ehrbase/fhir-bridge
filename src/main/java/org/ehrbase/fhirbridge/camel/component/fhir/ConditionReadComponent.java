package org.ehrbase.fhirbridge.camel.component.fhir;

import org.ehrbase.fhirbridge.fhir.ConditionReadTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class ConditionReadComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public ConditionReadComponent() {
        super(new ConditionReadTransaction());
    }
}
