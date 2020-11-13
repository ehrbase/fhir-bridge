package org.ehrbase.fhirbridge.fhir.patient;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class CreatePatientTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public CreatePatientTransaction() {
        super("pt-create",
                "Create Patient",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new CreatePatientProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
