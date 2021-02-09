package org.ehrbase.fhirbridge.camel.component.fhir.bundle;

import org.ehrbase.fhirbridge.fhir.bundle.ProvideBundleTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class ProvideBundleComponent extends CustomFhirComponent<FhirAuditDataset> {

    public ProvideBundleComponent() {
        super(new ProvideBundleTransaction());
    }
}
