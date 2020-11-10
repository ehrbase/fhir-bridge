package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class SearchConditionTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public SearchConditionTransaction() {
        super("cond-search",
                "Search Condition",
                true,
                null,
                null,
                FhirVersionEnum.R4,
                new SearchConditionProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
