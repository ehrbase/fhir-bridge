package org.ehrbase.fhirbridge.camel.component.fhir.observation;

import org.ehrbase.fhirbridge.fhir.observation.ReadObservationTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
public class ReadObservationComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public ReadObservationComponent() {
        super(new ReadObservationTransaction());
    }
}
