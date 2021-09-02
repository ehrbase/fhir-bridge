package org.ehrbase.fhirbridge.fhir.documentreference;

import org.hl7.fhir.r4.model.DocumentReference;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

import java.util.Optional;

public class CreateDocumentReferenceAuditStrategy extends GenericFhirAuditStrategy<DocumentReference> {

    public CreateDocumentReferenceAuditStrategy() {
        super(true, OperationOutcomeOperations.INSTANCE, documentReference -> Optional.of(documentReference.getSubject()));
    }
}
