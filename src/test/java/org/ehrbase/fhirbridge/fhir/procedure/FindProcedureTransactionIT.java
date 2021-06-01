package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Procedure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Find Procedure" transaction.
 */
class FindProcedureTransactionIT extends AbstractTransactionIT {

    @Test
    void findProcedureRead() throws IOException {
        MethodOutcome outcome = create("Procedure/transactions/provide-procedure-create.json");
        IIdType id = outcome.getId();

        Procedure procedure = read(id.getIdPart(), Procedure.class);

        Assertions.assertNotNull(procedure);
        Assertions.assertNotNull(procedure.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, procedure.getSubject().getIdentifier().getValue());
    }

    @Test
    void findProcedureVRead() throws IOException {
        MethodOutcome outcome = create("Procedure/transactions/provide-procedure-create.json");
        IIdType id = outcome.getId();

        Procedure procedure = vread(id.getIdPart(), id.getVersionIdPart(), Procedure.class);
        Assertions.assertNotNull(procedure);
        Assertions.assertNotNull(procedure.getId(), id.getIdPart());
        Assertions.assertNotNull(procedure.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, procedure.getSubject().getIdentifier().getValue());
    }

    @Test
    void findProcedureSearch() throws IOException {
        for (int i = 0; i < 3; i++) {
            create("Procedure/transactions/find-procedure-search.json");
        }

        Bundle bundle = search("Procedure?subject.identifier=" + PATIENT_ID + "&status=entered-in-error");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            Procedure procedure = (Procedure) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, procedure.getSubject().getIdentifier().getValue());
            Assertions.assertEquals(Procedure.ProcedureStatus.ENTEREDINERROR, procedure.getStatus());
        });
    }
}
