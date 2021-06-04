package org.ehrbase.fhirbridge.camel.component.fhir.encounter;

import org.ehrbase.fhirbridge.fhir.encounter.FindEncounterTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

/**
 * Camel {@link org.apache.camel.Component Component} that handles 'Find Encounter' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"java:S110"})
public class FindEncounterComponent extends CustomFhirComponent<FhirQueryAuditDataset> {

    public FindEncounterComponent() {
        super(new FindEncounterTransaction());
    }
}
