package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class ReadProcedureTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ReadProcedureTransaction() {
        super("proc-read",
                "Read Procedure",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new ReadProcedureProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
