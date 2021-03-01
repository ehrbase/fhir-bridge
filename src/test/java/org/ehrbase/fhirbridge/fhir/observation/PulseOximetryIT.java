package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOximetryConverter;
import org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund.RadiologischerBefundConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieObservation;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void mappingNormalFinding() throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource("create-pulse-oximetry.json");
        PulseOximetryConverter pulseOximetryConverter = new PulseOximetryConverter();
        PulsoxymetrieComposition mappedPulsoxymetrieComposition = pulseOximetryConverter.toComposition(observation);
        Diff diff = compareCompositions(getJavers(), "paragon-create-pulse-oximetry.json", mappedPulsoxymetrieComposition);

        assertEquals(diff.getChanges().size(), 0);
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(PulsoxymetrieComposition.class, List.of("location")))
                .registerValueObject(PulsoxymetrieObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String resource) throws IOException {
        return null;
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {

    }
}
