package org.ehrbase.fhirbridge.fhir.immunization;

import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Immunization;
import org.javers.core.Javers;

import java.io.IOException;

/**
 * Integration tests for {@link Immunization} using History of Vaccination profile.
 */
public class HistoryOfVaccinationIT extends AbstractMappingTestSetupIT {
    public HistoryOfVaccinationIT(String directory, Class clazz) {
        super("Immunization/", Immunization.class);
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
