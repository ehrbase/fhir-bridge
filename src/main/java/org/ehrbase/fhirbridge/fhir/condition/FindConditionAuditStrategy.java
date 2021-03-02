package org.ehrbase.fhirbridge.fhir.condition;

import org.ehrbase.fhirbridge.fhir.common.audit.FhirBridgeEventType;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.model.AuditMessage;
import org.openehealth.ipf.commons.ihe.core.atna.event.QueryInformationBuilder;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditDataset;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditStrategy FhirAuditStrategy} for Find Condition transaction.
 *
 * @since 1.0.0
 */
public class FindConditionAuditStrategy extends FhirQueryAuditStrategy {

    public FindConditionAuditStrategy(boolean serverSide) {
        super(serverSide, OperationOutcomeOperations.INSTANCE);
    }

    @Override
    public AuditMessage[] makeAuditMessage(AuditContext auditContext, FhirQueryAuditDataset auditDataset) {
        return new QueryInformationBuilder<>(auditContext, auditDataset, FhirBridgeEventType.FindCondition)
                .addPatients(auditDataset.getPatientIds())
                .getMessages();
    }
}
