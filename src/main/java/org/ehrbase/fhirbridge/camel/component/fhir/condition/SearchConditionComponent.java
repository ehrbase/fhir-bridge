package org.ehrbase.fhirbridge.camel.component.fhir.condition;

import org.ehrbase.fhirbridge.fhir.condition.SearchConditionTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class SearchConditionComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public SearchConditionComponent() {
        super(new SearchConditionTransaction());
    }
}
