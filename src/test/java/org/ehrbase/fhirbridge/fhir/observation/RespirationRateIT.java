package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate.RespiratoryRateCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzKategorieElement;
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
    void createRespirationRate() throws IOException {
        create("create-respiratory-rate.json");
    }

    @Test
    void createRespirationRateMagnitudeMin() throws IOException {
        testMapping("create-respiratory-rate_magnitude-min.json", "paragon-respiratory-rate_magnitude-min.json");
    }

    @Test
    void createRespirationRateMagnitudeMax() throws IOException {
        testMapping("create-respiratory-rate_magnitude-max.json", "paragon-respiratory-rate_magnitude-max.json");
    }

    @Test
    void createRespirationRateDataAbsent() throws IOException {
        testMapping("create-respiratory-rate_data-absent.json", "paragon-respiratory-rate_data-absent.json");
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(AtemfrequenzComposition.class, List.of("location",  "feederAudit")))
                .registerValueObject(AtemfrequenzObservation.class)
                .registerValueObject(AtemfrequenzKategorieElement.class)
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
