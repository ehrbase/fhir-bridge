package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Find Condition" transaction.
 */
class FindConditionTransactionIT extends AbstractTransactionIT {

    @Test
    void findConditionRead() throws IOException {
        MethodOutcome outcome = create("Condition/transactions/provide-condition-create.json");
        IIdType id = outcome.getId();

        Condition condition = read(id.getIdPart(), Condition.class);

        Assertions.assertNotNull(condition);
        Assertions.assertNotNull(condition.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, condition.getSubject().getIdentifier().getValue());
    }

    @Test
    void findConditionVRead() throws IOException {
        MethodOutcome outcome = create("Condition/transactions/provide-condition-create.json");
        IIdType id = outcome.getId();

        Condition condition = vread(id.getIdPart(), id.getVersionIdPart(), Condition.class);
        Assertions.assertNotNull(condition);
        Assertions.assertNotNull(condition.getId(), id.getIdPart());
        Assertions.assertNotNull(condition.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, condition.getSubject().getIdentifier().getValue());
    }

    @Test
    void findConditionSearch() throws IOException {
        for (int i = 0; i < 3; i++) {
            create("Condition/transactions/find-condition-search.json");
        }

        Bundle bundle = search("Condition?subject.identifier=" + PATIENT_ID + "&clinical-status=recurrence&verification-status=refuted");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            var condition = (Condition) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, condition.getSubject().getIdentifier().getValue());
            Assertions.assertEquals("refuted", condition.getVerificationStatus().getCodingFirstRep().getCode());
            Assertions.assertEquals("recurrence", condition.getClinicalStatus().getCodingFirstRep().getCode());
        });
    }
}
