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
public class ProvideEncounterTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ProvideEncounterTransaction() {
        super("encounter-provide",
                "Provide Encounter",
                false,
                null,
                new ProvideEncounterAuditStrategy(),
                FhirVersionEnum.R4,
                new ProvideEncounterProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
