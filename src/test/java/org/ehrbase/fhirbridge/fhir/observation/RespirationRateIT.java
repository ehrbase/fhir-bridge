package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate.RespiratoryRateCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
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

public class RespirationRateIT extends AbstractMappingTestSetupIT {

    public RespirationRateIT() {
        super("Observation/RespirationRate/", Observation.class);
    }

    @Test
    void createRespirationrate() throws IOException {
        testMapping("create-respiration-rate", "paragon-create-respiratory-rate.json");
    }

    @Test
    void createRespirationrate_2() throws IOException {
        testMapping("create-respiration-rate", "paragon-create-respiratory-rate-2.json");
    }

    @Test
    void createRespirationrate_3() throws IOException {
        testMapping("create-respiration-rate", "paragon-create-respiratory-rate-on-ventilator.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(AtemfrequenzComposition.class, List.of("location",  "feederAudit")))
                .registerValueObject(AtemfrequenzObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new RespiratoryRateCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        RespiratoryRateCompositionConverter respiratoryRateCompositionConverter = new RespiratoryRateCompositionConverter();
        AtemfrequenzComposition mapped = respiratoryRateCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
