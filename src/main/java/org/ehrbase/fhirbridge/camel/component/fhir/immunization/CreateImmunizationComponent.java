package org.ehrbase.fhirbridge.camel.component.fhir.immunization;

import org.ehrbase.fhirbridge.fhir.immunization.CreateImmunizationTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

@SuppressWarnings({"java:S110"})
/**
 * Camel {@link org.apache.camel.Component Component} that handles 'Create Immunization' transaction.
 *
 * @since 1.0.0
 */

public class CreateImmunizationComponent extends CustomFhirComponent<GenericFhirAuditDataset> {
    public CreateImmunizationComponent() {
        super(new CreateImmunizationTransaction());
    }
}

