package org.ehrbase.fhirbridge.fhir.procedure;

import org.hl7.fhir.r4.model.Procedure;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

import java.util.Optional;

public class CreateProcedureAuditStrategy extends GenericFhirAuditStrategy<Procedure> {

    public CreateProcedureAuditStrategy(boolean serverSide) {
        super(serverSide, OperationOutcomeOperations.INSTANCE, procedure -> Optional.of(procedure.getSubject()));
    }
}
