package org.ehrbase.fhirbridge.fhir.encounter;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

/**
 * Configuration for 'Create Encounter' transaction.
 *
 * @since 1.0.0
 */
public class CreateEncounterTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public CreateEncounterTransaction() {
        super("encounter-create",
                "Create Encounter",
                false,
                null,
                new CreateEncounterAuditStrategy(),
                FhirVersionEnum.R4,
                new CreateEncounterProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
