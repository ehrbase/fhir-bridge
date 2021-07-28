package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus.PregnancyStatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.SchwangerschaftsstatusComposition;
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

public class PregnancyStatusIT extends AbstractMappingTestSetupIT {

    public PregnancyStatusIT() {
        super("Observation/PregnancyStatus/", Observation.class); //fhir-Resource
    }

    @Test
    void createPregnancyStatus() throws IOException {
        create("create-pregnancy-status.json");
    }

    @Test
    void testMappingPregnancyStatus() throws IOException {
        testMapping("create-pregnancy-status.json",
                "paragon-create-pregnancy-status.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(SchwangerschaftsstatusComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(SchwangerschaftsstatusObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new PregnancyStatusCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        PregnancyStatusCompositionConverter pregnancyStatusCompositionConverter = new PregnancyStatusCompositionConverter();
        SchwangerschaftsstatusComposition mapped = pregnancyStatusCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
