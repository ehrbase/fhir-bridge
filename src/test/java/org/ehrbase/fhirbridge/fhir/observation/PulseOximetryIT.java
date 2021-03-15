package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specificconverters.pulseoximetry.PulseOximetryConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
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
        create("create-pulse-oximetry-arterial.json");
    }

    @Test
    void mappingArterial() throws IOException {
        testMapping("create-pulse-oximetry-arterial.json", "paragon-create-pulse-oximetry-arterial.json");
    }

    @Test
    void mappingByPulseOximetry() throws IOException {
        testMapping("create-pulse-oximetry-by-oximetry.json", "paragon-create-pulse-oximetry-by-oximetry.json");
    }

    @Test
    void mappingPheriperalSaturation() throws IOException {
        testMapping("create-pulse-oximetry-pheriperal-saturation.json", "paragon-create-pulse-oximetry-pheriperal-saturation.json");
    }

    @Test
    void mappingInvalid() throws IOException {
        Exception exception = executeMappingException("create-pulse-oximetry-invalid.json");
        assertEquals("The Code of code.coding is not supported for the Fhir-Bridge, or duplicated within the resource. If the LOINC-code 20564-1 or 2708-6 AND 20564-1 was entered, the oxygen Saturation has to be send as part of a Blood gas panel. It can not be processed as a single resource in this cases.", exception.getMessage());

    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(PulsoxymetrieComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(PulsoxymetrieObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String resource) throws IOException {
        Observation pulseOximetry = (Observation) testFileLoader.loadResource(resource);
        return assertThrows(ConversionException.class, () -> new PulseOximetryConverter().convert(pulseOximetry));
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        PulseOximetryConverter pulseOximetryConverter = new PulseOximetryConverter();
        PulsoxymetrieComposition mappedPulsoxymetrieComposition = pulseOximetryConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedPulsoxymetrieComposition);
        assertEquals(diff.getChanges().size(), 0);

    }
}
