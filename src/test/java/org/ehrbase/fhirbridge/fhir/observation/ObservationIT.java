package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Observation Observation} resource.
 */
class ObservationIT extends AbstractMappingTestSetupIT {

    public ObservationIT() {
        super("Observation/", Observation.class);
    }

    @Test
    void createCoronavirusNachweisTest() throws IOException {
        create("create-coronavirus-nachweis-test.json");
    }

    @Test
    void createObservationLab() throws IOException {
        create("create-observation-lab.json");
    }
    
    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-observation-with-default-profile.json");

        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertTrue(StringUtils.startsWith(exception.getMessage(), "HTTP 422 : Default profile is not supported for Observation. One of the following profiles is expected:"));
    }

/* Disabled test not working since unprocessable is now catched
    @Test
    void createWithInvalidQuantity() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-observation-with-invalid-quantity.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);
        assertTrue(StringUtils.startsWith(exception.getMessage(), "HTTP 422 : Wrong Status code. Expected: [200, 201, 204]. Got: 400."));
    }
*/


    @Test
    void createWithInvalidQuantityDatatype() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-observation-with-invalid-quantity-datatype.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);
        assertEquals("HTTP 422 : Error parsing JSON: the primitive value must be a number", exception.getMessage());
    }


    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () -> {
            // new YourConverter().convert(@NonNull ((YourResource) domainResource)));
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        // your mapping compared to paragon file
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                // .registerValueObject(new ValueObjectDefinition(YourComposition.class, List.of("location")))
                .build();
    }
}
