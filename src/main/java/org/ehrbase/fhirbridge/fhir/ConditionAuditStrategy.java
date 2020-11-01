package org.ehrbase.fhirbridge.fhir;

import org.hl7.fhir.r4.model.Condition;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

import java.util.Optional;

public class ConditionAuditStrategy extends GenericFhirAuditStrategy<Condition> {

    public ConditionAuditStrategy(boolean serverSide) {
        super(serverSide, OperationOutcomeOperations.INSTANCE, condition -> Optional.of(condition.getSubject()));
    }
}
