package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class ReadConditionTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ReadConditionTransaction() {
        super("cond-find",
                "Find Condition",
                true,
                null,
                null,
                FhirVersionEnum.R4,
                new ReadConditionProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
