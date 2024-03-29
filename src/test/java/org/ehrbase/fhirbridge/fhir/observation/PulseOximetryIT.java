package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry.PulseOximetryCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieObservation;
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

/**
 * Integration tests for {@link Observation Observation} resource.
 */
class PulseOximetryIT extends AbstractMappingTestSetupIT {

    public PulseOximetryIT() {
        super("Observation/PulseOximetry/", Observation.class);
    }

    @Test
    void createPulseOxymetry() throws IOException {
        create("create-pulse-oximetry.json");
    }

    @Test
    void testPulseOxymetryMagnitudeMin() throws IOException {
        testMapping("create-pulse-oximetry-magnitude-min.json",
                "paragon-pulse-oximetry-magnitude-min.json");
    }

    @Test
    void testPulseOxymetryMagnitudeMax() throws IOException {
        testMapping("create-pulse-oximetry-magnitude-max.json",
                "paragon-pulse-oximetry-magnitude-max.json");
    }

    @Test
    void testPulseOxymetryDataAbsent() throws IOException {
        testMapping("create-pulse-oximetry-data-absent.json",
                "paragon-pulse-oximetry-data-absent.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(PulsoxymetrieComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(PulsoxymetrieObservation.class)
                .registerValueObject(PulsoxymetrieKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String resource) throws IOException {
        Observation pulseOximetry = (Observation) testFileLoader.loadResource(resource);
        return assertThrows(Exception.class, () -> new PulseOximetryCompositionConverter().convert(pulseOximetry));
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        PulseOximetryCompositionConverter pulseOximetryConverter = new PulseOximetryCompositionConverter();
        PulsoxymetrieComposition mappedPulsoxymetrieComposition = pulseOximetryConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedPulsoxymetrieComposition);
        assertEquals(diff.getChanges().size(), 0);
    }
}
