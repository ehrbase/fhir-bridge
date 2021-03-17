package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.knownexposure.KnownExposureConverter;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.SARSCoV2ExpositionComposition;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionEvaluation;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KnownExposureIT extends AbstractMappingTestSetupIT {

    public KnownExposureIT() {
        super("Observation/KnownExposure/", Observation.class); //fhir-Resource
    }

    @Test
    void createExposureOutput() throws IOException {
        create("create-known-exposure-unknown.json");
    }

    // #####################################################################################
    // check payload
    @Test
    void mappingAbsent() throws IOException {
        testMapping("create-known-exposure-absent.json",
                "paragon-known-exposure-absent.json");
    }

    @Test
    void mappingUnknown() throws IOException {
        testMapping("create-known-exposure-unknown.json",
                "paragon-known-exposure-unknown.json");
    }

    @Test
    void mappingPresent() throws IOException {
        testMapping("create-known-exposure-present.json",
                "paragon-known-exposure-present.json");
    }

    // #####################################################################################
    // check exceptions
    @Test
    void createInvalidCode() throws IOException {
        // copy of absent, manipulated line 58 to contain invalid snomed code
        Exception exception = executeMappingException("create-known-exposure-invalid.json");
        assertEquals("The SNOMED code 666 is not supported for known SarsCov2ExpositionConverter !", exception.getMessage());
    }

    @Test
    void createInvalidStatus() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-invalid-status.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("[element=\"status\"] Invalid attribute value \"invalidTestCode\": Unknown ObservationStatus code 'invalidTestCode'", dataFormatException.getMessage());
        }
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(SARSCoV2ExpositionComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(SarsCov2ExpositionEvaluation.class)

                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () ->
                new KnownExposureConverter().toComposition(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        KnownExposureConverter compositionConverter = new KnownExposureConverter();
        SARSCoV2ExpositionComposition mapped = compositionConverter.toComposition(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
