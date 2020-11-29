package org.ehrbase.fhirbridge.fhir.patient;

import org.hl7.fhir.r4.model.Patient;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

import java.util.Optional;

public class CreatePatientAuditStrategy extends GenericFhirAuditStrategy<Patient> {

    public CreatePatientAuditStrategy(boolean serverSide) {
        super(serverSide, OperationOutcomeOperations.INSTANCE, patient -> Optional.empty());
    }
}
