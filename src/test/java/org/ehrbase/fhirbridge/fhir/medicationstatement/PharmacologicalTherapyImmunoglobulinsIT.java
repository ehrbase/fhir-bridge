package org.ehrbase.fhirbridge.fhir.medicationstatement;

import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.javers.core.Javers;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration tests for {@link MedicationStatement} using Pharmacological Therapy Immunoglobulins profile.
 */
class PharmacologicalTherapyImmunoglobulinsIT extends AbstractMappingTestSetupIT {

    public PharmacologicalTherapyImmunoglobulinsIT() {
        super("MedicationStatement/", MedicationStatement.class);
    }

    @Test
    void createPharmacologicalTherapyImmunoglobulins() throws IOException {
        create("create-pharmacological-therapy-immunoglobulins.json");
    }

    @Override
    public Javers getJavers() {
        return null;
    }

    @Override
    public Exception executeMappingException(String resource) throws IOException {
        return null;
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {

    }
}
