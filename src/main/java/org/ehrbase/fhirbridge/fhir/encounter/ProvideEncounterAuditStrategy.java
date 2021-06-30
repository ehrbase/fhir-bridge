package org.ehrbase.fhirbridge.fhir.encounter;

import org.hl7.fhir.r4.model.Encounter;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

import java.util.Optional;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.core.atna.AuditStrategy AuditStrategy}
 * for 'Create Encounter' transaction.
 *
 * @since 1.0.0
 */
public class ProvideEncounterAuditStrategy extends GenericFhirAuditStrategy<Encounter> {

    public ProvideEncounterAuditStrategy() {
        super(true, OperationOutcomeOperations.INSTANCE, encounter -> Optional.of(encounter.getSubject()));
    }
}
