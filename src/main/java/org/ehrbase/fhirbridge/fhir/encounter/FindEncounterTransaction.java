package org.ehrbase.fhirbridge.fhir.encounter;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditDataset;

/**
 * Configuration for 'Find Encounter' transaction.
 *
 * @since 1.0.0
 */
public class FindEncounterTransaction extends FhirTransactionConfiguration<FhirQueryAuditDataset> {

    public FindEncounterTransaction() {
        super("encounter-find",
                "Find Encounter",
                true,
                null,
                new FindEncounterAuditStrategy(),
                FhirVersionEnum.R4,
                new FindEncounterProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
