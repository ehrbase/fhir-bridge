package org.ehrbase.fhirbridge.fhir.bundle;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;
import org.openehealth.ipf.commons.ihe.fhir.support.BatchTransactionClientRequestFactory;
import org.openehealth.ipf.commons.ihe.fhir.support.BatchTransactionResourceProvider;

public class ProvideBundleTransaction extends FhirTransactionConfiguration<FhirAuditDataset> {

    public ProvideBundleTransaction() {
        super("fhir-provide-bundle",
                "Provide Bundle",
                false,
                new ProvideBundleAuditStrategy(false),
                new ProvideBundleAuditStrategy(true),
                FhirVersionEnum.R4,
                BatchTransactionResourceProvider.getInstance(),
                BatchTransactionClientRequestFactory.getInstance(),
                FhirTransactionValidator.NO_VALIDATION);
    }
}
