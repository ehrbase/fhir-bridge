package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class ConditionReadTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ConditionReadTransaction() {
        super("condition-read",
                "Read Condition",
                false,
                new ConditionAuditStrategy(false),
                new ConditionAuditStrategy(true),
                FhirVersionEnum.R4,
                new ConditionReadProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
