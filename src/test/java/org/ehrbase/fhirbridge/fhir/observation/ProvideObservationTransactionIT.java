package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Observation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Integration Tests that validate "Provide Observation" transaction.
 */
class ProvideObservationTransactionIT extends AbstractTransactionIT {

    @Test
    void provideObservationCreate() throws Exception {
        var outcome = create("Observation/transactions/provide-observation-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void provideObservationConditionalUpdate() throws Exception {
        MethodOutcome outcome;

        outcome = create("Observation/transactions/provide-observation-create.json");
        var id = outcome.getId();

        outcome = update("Observation/transactions/provide-observation-update.json", "Observation?_id=" + id.getIdPart() + "&subject.identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        var observation = (Observation) outcome.getResource();

        Assertions.assertEquals(PATIENT_ID, observation.getSubject().getIdentifier().getValue());
        Assertions.assertEquals(Observation.ObservationStatus.CORRECTED, observation.getStatus());
    }
}
