
package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.parser.DataFormatException;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure.SarsCov2KnownExposureCompositionConverter;
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
    // check exception
    @Test
    void createInvalidStatus() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-invalid-status.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("[element=\"status\"] Invalid attribute value \"invalidTestCode\": Unknown ObservationStatus code 'invalidTestCode'", dataFormatException.getMessage());
        }
    }
    @Test
    void createMultipleKnownReasons() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-present-multiple.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("Multiple known expositions reasons are not possible in SarsCov2ExpositionConverter!", dataFormatException.getMessage());
        }
    }
    @Test
    void createWithoutKnownReasonMissingDataAbsent() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-unknown-zero.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("Zero or Multiple data absent reasons are not possible in SarsCov2ExpositionConverter with no valid exposition information!", dataFormatException.getMessage());
        }
    }
    @Test
    void createWithoutKnownReasonMultipleDataAbsent() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-unknown-multiple.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("Zero or Multiple data absent reasons are not possible in SarsCov2ExpositionConverter with no valid exposition information!", dataFormatException.getMessage());
        }
    }
    @Test
    void createWithWrongSystemInDataAbsent() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-present-wrong-system.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("Cannot set 'unknown' in SarsCov2ExpositionConverter with no valid data absent reason coding!", dataFormatException.getMessage());
        }
    }
    @Test
    void createWithWrongCodeInDataAbsent() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-present-wrong-code.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("Cannot set 'unknown' in SarsCov2ExpositionConverter with no valid data absent reason coding!", dataFormatException.getMessage());
        }
    }
    @Test
    void createWithWrongSystem() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-unknown-wrong-system.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("\"The SNOMED code '\" + code + \"' in system '\" + system + \"' is not supported for known SarsCov2ExpositionConverter!", dataFormatException.getMessage());
        }
    }
    @Test
    void createWithWrongCode() throws IOException {
        try{
            super.testFileLoader.loadResource("create-known-exposure-unknown-wrong-code.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("\"The SNOMED code '\" + code + \"' in system '\" + system + \"' is not supported for known SarsCov2ExpositionConverter!", dataFormatException.getMessage());
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
        return assertThrows(ConversionException.class, () ->
                new SarsCov2KnownExposureCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        SarsCov2KnownExposureCompositionConverter compositionConverter = new SarsCov2KnownExposureCompositionConverter();
        SARSCoV2ExpositionComposition mapped = compositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }


}

