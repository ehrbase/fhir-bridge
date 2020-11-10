package org.ehrbase.fhirbridge.audit;

import org.openehealth.ipf.commons.audit.AuditMessagePostProcessor;
import org.openehealth.ipf.commons.audit.model.AuditMessage;

public class Test implements AuditMessagePostProcessor {

    @Override
    public AuditMessage apply(AuditMessage auditMessage) {
        return null;
    }
}
