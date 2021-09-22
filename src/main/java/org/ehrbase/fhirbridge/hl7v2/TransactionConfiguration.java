package org.ehrbase.fhirbridge.hl7v2;

import ca.uhn.hl7v2.ErrorCode;
import ca.uhn.hl7v2.Version;
import org.openehealth.ipf.commons.ihe.hl7v2.Hl7v2TransactionConfiguration;
import org.openehealth.ipf.commons.ihe.hl7v2.definitions.HapiContextFactory;
import org.springframework.stereotype.Component;

@Component
public class TransactionConfiguration extends Hl7v2TransactionConfiguration<AuditDataset> {

    public TransactionConfiguration() {
        super(
                "mdm",
                "Medical Document Management",
                false,
                null,
                null,
                new Version[]{Version.V25},
                "MDM adapter",
                "IPF",
                ErrorCode.APPLICATION_INTERNAL_ERROR,
                ErrorCode.APPLICATION_INTERNAL_ERROR,
                new String[]{"MDM"},
                new String[]{"T02"},
                new String[]{"*"},
                new String[]{"*"},
                new boolean[]{true},
                new boolean[]{false},
                HapiContextFactory.createHapiContext());
    }
}
