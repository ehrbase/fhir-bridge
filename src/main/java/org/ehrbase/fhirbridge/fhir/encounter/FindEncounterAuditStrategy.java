package org.ehrbase.fhirbridge.fhir.encounter;

import org.ehrbase.fhirbridge.fhir.FhirBridgeEventType;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.model.AuditMessage;
import org.openehealth.ipf.commons.ihe.core.atna.event.QueryInformationBuilder;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditDataset;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.core.atna.AuditStrategy AuditStrategy}
 * for 'Find Encounter' transaction.
 *
 * @since 1.0.0
 */
public class FindEncounterAuditStrategy extends FhirQueryAuditStrategy {

    public FindEncounterAuditStrategy() {
        super(true, OperationOutcomeOperations.INSTANCE);
    }

    @Override
    public AuditMessage[] makeAuditMessage(AuditContext auditContext, FhirQueryAuditDataset auditDataset) {
        return new QueryInformationBuilder<>(auditContext, auditDataset, FhirBridgeEventType.FIND_ENCOUNTER)
                .addPatients(auditDataset.getPatientIds())
                .getMessages();
    }
}
