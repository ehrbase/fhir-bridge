package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Find Observation" transaction.
 */
class FindObservationTransactionIT extends AbstractTransactionIT {

    @Test
    void findObservationRead() throws IOException {
        MethodOutcome outcome = create("Observation/transactions/provide-observation-create.json");
        IIdType id = outcome.getId();

        Observation observation = read(id.getIdPart(), Observation.class);

        Assertions.assertNotNull(observation);
        Assertions.assertNotNull(observation.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, observation.getSubject().getIdentifier().getValue());
    }

    @Test
    void findObservationVRead() throws IOException {
        MethodOutcome outcome = create("Observation/transactions/provide-observation-create.json");
        IIdType id = outcome.getId();

        Observation observation = vread(id.getIdPart(), id.getVersionIdPart(), Observation.class);
        Assertions.assertNotNull(observation);
        Assertions.assertNotNull(observation.getId(), id.getIdPart());
        Assertions.assertNotNull(observation.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, observation.getSubject().getIdentifier().getValue());
    }

    @Test
    void findObservationSearch() throws IOException {
        for (int i = 0; i < 3; i++) {
            create("Observation/transactions/find-observation-search.json");
        }

        Bundle bundle = search("Observation?subject.identifier=" + PATIENT_ID + "&status=final");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            Observation observation = (Observation) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, observation.getSubject().getIdentifier().getValue());
            Assertions.assertEquals(Observation.ObservationStatus.FINAL, observation.getStatus());
        });
    }
}
