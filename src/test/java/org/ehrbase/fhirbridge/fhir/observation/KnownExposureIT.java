package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.parser.DataFormatException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure.SarsCov2KnownExposureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.SARSCoV2ExpositionComposition;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.SarsCov2ExpositionKategorieElement;
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
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(SARSCoV2ExpositionComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(SarsCov2ExpositionEvaluation.class)
                .registerValueObject(SarsCov2ExpositionKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
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

