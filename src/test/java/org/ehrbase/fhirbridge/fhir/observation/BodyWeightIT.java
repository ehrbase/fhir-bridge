package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight.BodyWeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.KoerpergewichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
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

public class BodyWeightIT extends AbstractMappingTestSetupIT {

    public BodyWeightIT() {
        super("Observation/BodyWeight/", Observation.class); //fhir-Resource
    }

    @Test
    void createBodyWeight() throws IOException {
        create("create-body-weight.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void testBodyWeightMagnitudeMin() throws IOException {
        testMapping("create-body-weight_magnitude-min.json",
                "paragon-body-weight_magnitude-min.json");
    }

    @Test
    void testBodyWeightMagnitudeMax() throws IOException {
        testMapping("create-body-weight_magnitude-max.json",
                "paragon-body-weight_magnitude-max.json");
    }

    @Test
    void testBodyWeightUnitGramm() throws IOException {
        testMapping("create-body-weight_gramm.json",
                "paragon-body-weight_gramm.json");
    }

    @Test
    void testBodyWeightUnitPound() throws IOException {
        testMapping("create-body-weight_pound.json",
                "paragon-body-weight_pound.json");
    }

    @Test
    void testBodyWeightDataAbsent() throws IOException {
        testMapping("create-body-weight_data-absent.json",
                "paragon-body-weight_data-absent.json");
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KoerpergewichtComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(KoerpergewichtObservation.class)
                .registerValueObject(KoerpergewichtKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new BodyWeightCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        BodyWeightCompositionConverter bodyWeightCompositionConverter = new BodyWeightCompositionConverter();
        KoerpergewichtComposition mapped = bodyWeightCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
