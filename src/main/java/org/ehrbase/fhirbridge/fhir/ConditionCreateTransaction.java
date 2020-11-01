package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class ConditionCreateTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ConditionCreateTransaction() {
        super("condition-create",
                "Create Condition",
                false,
                new ConditionAuditStrategy(false),
                new ConditionAuditStrategy(true),
                FhirVersionEnum.R4,
                new ConditionCreateProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
