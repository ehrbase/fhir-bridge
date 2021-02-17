package org.ehrbase.fhirbridge.fhir.bundle;

import ca.uhn.fhir.rest.gclient.ITransactionTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.BloodGasPanelCompositionConverter;
import org.ehrbase.fhirbridge.fhir.AbstractBundleMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.converter.BloodGasPanelConverter;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.javers.core.Javers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Bundle Bundle} resource.
 */
public class BloodGasPanelIT extends AbstractBundleMappingTestSetupIT {


    public BloodGasPanelIT() {
        super("Bundle/", Bundle.class);
    }

    @Test
    public void createBloodGas() throws IOException {
        create("create-blood-gas.json");
    }


    @Test
    void createInvalidDuplicatedProfile() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-blood-gas-invalid-duplicated-profile.json"));
        assertEquals("The SNOMED code: asdasd, is not supported for radiology report !", exception.getMessage());
    }

    @Test
    void createInvalidMember() throws IOException {
        Exception exception = executeIntegrationTestWithException("create-blood-gas-invalid-member.json");
        assertEquals("The SNOMED code: asdasd, is not supported for radiology report !", exception.getMessage());
    }

    @Test
    void createInvalidMemberCount() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-blood-gas-invalid-member-count.json"));
        assertEquals("The SNOMED code: asdasd, is not supported for radiology report !", exception.getMessage());
    }

    @Test
    void createInvalidMissingProfile() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-blood-gas-invalid-missing-profile.json"));
        assertEquals("Resource 'urn:uuid:04121321-4af5-424c-a0e1-ed3aab1casd1' is missing", exception.getMessage());
    }

    @Test
    void createInvalidProfile() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-blood-gas-invalid-profile.json"));
        assertEquals("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/inhaled-oxygen-concentration from the Bundle.", exception.getMessage());
    }

    @Test
    void createInvalidWithPanelMissing() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-blood-gas-invalid-with-panel-missing.json"));
        assertEquals("Root resource with profile 'https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel' is missing", exception.getMessage());
    }

/*    @Test
    void createInvalidSubjectId() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-bloodgas-invalid-subject-ids.json"));
        assertEquals("The subject Ids of the profile within the Bundle reference different Patient. A Blood Gas Panel must refer to one identical Patient!", exception.getMessage());
    }*/

    @Override
    public Javers getJavers() {
        return null;
    }

    @Override
    public Exception executeMappingUnprocessableEntityException(IBaseResource bloodgasPanel) {
        return assertThrows(UnprocessableEntityException.class, () -> {
            new BloodGasPanelCompositionConverter().toComposition(new BloodGasPanelConverter().convert((Bundle) bloodgasPanel));
        });
    }

    public Exception executeIntegrationTestWithException(String path) throws IOException {
        String resource = testFileLoader.loadResourceToString(path);
        ITransactionTyped<String> createdType = client.transaction().withBundle(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        return Assertions.assertThrows(UnprocessableEntityException.class, createdType::execute);
    }

}

