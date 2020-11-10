package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class CreateConditionTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public CreateConditionTransaction() {
        super("cond-create",
                "Create Condition",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new CreateConditionProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
