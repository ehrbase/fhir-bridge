package org.ehrbase.fhirbridge.camel.component.fhir.encounter;

import org.ehrbase.fhirbridge.fhir.encounter.CreateEncounterTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

/**
 * Camel {@link org.apache.camel.Component Component} that handles 'Create Encounter' transaction.
 *
 * @since 1.0.0
 */
public class CreateEncounterComponent extends CustomFhirComponent<GenericFhirAuditDataset> {

    public CreateEncounterComponent() {
        super(new CreateEncounterTransaction());
    }
}
