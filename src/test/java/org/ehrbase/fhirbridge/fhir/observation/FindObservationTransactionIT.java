package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
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
    void findObservationRead() throws Exception {
        var outcome = create("Observation/transactions/provide-observation-create.json");
        var id = outcome.getId();

        var observation = read(id.getIdPart(), Observation.class);

        Assertions.assertNotNull(observation);
        Assertions.assertNotNull(observation.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, observation.getSubject().getIdentifier().getValue());
    }

    @Test
    void findObservationVRead() throws Exception {
        var outcome = create("Observation/transactions/provide-observation-create.json");
        var id = outcome.getId();

        var observation = vread(id.getIdPart(), id.getVersionIdPart(), Observation.class);
        Assertions.assertNotNull(observation);
        Assertions.assertNotNull(observation.getId(), id.getIdPart());
        Assertions.assertNotNull(observation.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, observation.getSubject().getIdentifier().getValue());
    }

    @Test
    void findObservationSearch() throws IOException {
        create("Observation/transactions/find-observation-search.json");
        create("Observation/transactions/find-observation-search.json");
        create("Observation/transactions/find-observation-search.json");

        Bundle bundle = search("Observation?subject.identifier=" + PATIENT_ID + "&status=preliminary");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            var observation = (Observation) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, observation.getSubject().getIdentifier().getValue());
            Assertions.assertEquals(Observation.ObservationStatus.PRELIMINARY, observation.getStatus());
        });
    }
}
