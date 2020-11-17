package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class FindProcedureTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public FindProcedureTransaction() {
        super("fhir-find-procedure",
                "Find Procedure",
                true,
                null,
                null,
                FhirVersionEnum.R4,
                new FindProcedureProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
