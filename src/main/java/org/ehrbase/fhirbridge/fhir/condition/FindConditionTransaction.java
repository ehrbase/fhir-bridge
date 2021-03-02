package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditDataset;

/**
 * {@link FhirTransactionConfiguration} for Find Condition transaction.
 *
 * @since 1.0.0
 */
public class FindConditionTransaction extends FhirTransactionConfiguration<FhirQueryAuditDataset> {

    public FindConditionTransaction() {
        super("fhir-find-condition",
                "Find Condition",
                true,
                null,
                new FindConditionAuditStrategy(true),
                FhirVersionEnum.R4,
                new FindConditionProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
