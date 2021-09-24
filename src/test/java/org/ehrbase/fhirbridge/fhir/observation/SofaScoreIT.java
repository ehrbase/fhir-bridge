package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.sofascore.SofaScoreCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition.SofaScoreKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition.SofaScoreObservation;
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

class SofaScoreIT extends AbstractMappingTestSetupIT {

    public SofaScoreIT() {
        super("Observation/SofaScore/", Observation.class);
    }

    @Test
    void createSofaScore() throws IOException {
        create("create-sofa-score.json");
    }

    @Test
    void createSofaScoreMapping() throws IOException {
        testMapping("create-sofa-score.json", "paragon-create-sofa-score.json");
    }

    @Test
    void createSofaScoreGesamtergebnisMapping() throws IOException {
        testMapping("create-sofa-score_gesamtergebnis.json", "paragon-create-sofa-score_gesamtergebnis.json");
    }

    @Test
    void createWithAbsentReason() throws IOException {
        testMapping("create-data-absent-kidneys.json", "paragon-create-data-absent-kidneys.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(SOFAComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(SofaScoreObservation.class)
                .registerValueObject(SofaScoreKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String resource) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(resource);
        SofaScoreCompositionConverter converter = new SofaScoreCompositionConverter();
        return assertThrows(ConversionException.class, () -> converter.convert(observation));
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        SofaScoreCompositionConverter converter = new SofaScoreCompositionConverter();
        SOFAComposition composition = converter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, composition);
        assertEquals(0, diff.getChanges().size());
    }
}
