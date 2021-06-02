package org.ehrbase.fhirbridge.camel.component.fhir.encounter;

import org.ehrbase.fhirbridge.fhir.encounter.ProvideEncounterTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

/**
 * Camel {@link org.apache.camel.Component Component} that handles 'Provide Encounter' transaction.
 *
 * @since 1.0.0
 */
public class ProvideEncounterComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public ProvideEncounterComponent() {
        super(new ProvideEncounterTransaction());
    }
}
