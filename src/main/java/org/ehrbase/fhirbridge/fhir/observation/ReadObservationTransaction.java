package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class ReadObservationTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ReadObservationTransaction() {
        super("obs-read",
                "Read Observation",
                true,
                null,
                null,
                FhirVersionEnum.R4,
                new ReadObservationProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
