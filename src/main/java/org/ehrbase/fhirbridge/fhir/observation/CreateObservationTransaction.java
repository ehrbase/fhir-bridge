package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class CreateObservationTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public CreateObservationTransaction() {
        super("obs-create",
                "Create Observation",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new CreateObservationProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
