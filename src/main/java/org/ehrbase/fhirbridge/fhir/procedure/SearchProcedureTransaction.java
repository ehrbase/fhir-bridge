package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class SearchProcedureTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public SearchProcedureTransaction() {
        super("proc-search",
                "Search Procedure",
                true,
                null,
                null,
                FhirVersionEnum.R4,
                new SearchProcedureProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
