package org.ehrbase.fhirbridge.fhir.medicationstatement;

import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Find Medication Statement" transaction.
 */
class FindMedicationStatementTransactionIT extends AbstractTransactionIT {

    @Test
    void findMedicationStatementRead() throws Exception {
        var outcome = create("MedicationStatement/transactions/provide-medication-statement-create.json");
        var id = outcome.getId();

        var medicationStatement = read(id.getIdPart(), MedicationStatement.class);

        Assertions.assertNotNull(medicationStatement);
        Assertions.assertNotNull(medicationStatement.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, medicationStatement.getSubject().getIdentifier().getValue());
    }

    @Test
    void findMedicationStatementVRead() throws Exception {
        var outcome = create("MedicationStatement/transactions/provide-medication-statement-create.json");
        var id = outcome.getId();

        var medicationStatement = vread(id.getIdPart(), id.getVersionIdPart(), MedicationStatement.class);
        Assertions.assertNotNull(medicationStatement);
        Assertions.assertNotNull(medicationStatement.getId(), id.getIdPart());
        Assertions.assertNotNull(medicationStatement.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, medicationStatement.getSubject().getIdentifier().getValue());
    }

    @Test
    void findMedicationStatementSearch() throws IOException {
        create("MedicationStatement/transactions/find-medication-statement-search.json");
        create("MedicationStatement/transactions/find-medication-statement-search.json");
        create("MedicationStatement/transactions/find-medication-statement-search.json");

        Bundle bundle = search("MedicationStatement?subject.identifier=" + PATIENT_ID + "&status=not-taken");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            var medicationStatement = (MedicationStatement) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, medicationStatement.getSubject().getIdentifier().getValue());
            Assertions.assertEquals(MedicationStatement.MedicationStatementStatus.NOTTAKEN, medicationStatement.getStatus());
        });
    }
}
