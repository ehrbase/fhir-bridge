package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
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

    // #####################################################################################
    // check payload

    @Test
    void testSofaScoreAllComponentCodesZero() throws IOException {
        testMapping("create-sofa-score-all-component-codes-zero.json",
                "paragon-sofa-score-all-component-codes-zero.json");
    }

    @Test
    void testSofaScoreAllComponentCodesOne() throws IOException {
        testMapping("create-sofa-score-all-component-codes-one.json",
                "paragon-sofa-score-all-component-codes-one.json");
    }

    @Test
    void testSofaScoreAllComponentCodesTwo() throws IOException {
        testMapping("create-sofa-score-all-component-codes-two.json",
                "paragon-sofa-score-all-component-codes-two.json");
    }

    @Test
    void testSofaScoreAllComponentCodesThree() throws IOException {
        testMapping("create-sofa-score-all-component-codes-three.json",
                "paragon-sofa-score-all-component-codes-three.json");
    }

    @Test
    void testSofaScoreAllComponentCodesFour() throws IOException {
        testMapping("create-sofa-score-all-component-codes-four.json",
                "paragon-sofa-score-all-component-codes-four.json");
    }

    @Test
    void testSofaScoreAllComponentDataAbsent() throws IOException {
        testMapping("create-sofa-score-all-component-data-absent.json",
                "paragon-sofa-score-all-component-data-absent.json");
    }

    // #####################################################################################
    // check exceptions

    @Test
    void createSofaScoreInvalidCodeResp() throws IOException {
        // copy of create-sofa-score-all-component-codes-zero, manipulated line 57
        Exception exception = executeMappingException("create-sofa-score-invalid-code-resp.json");
        assertEquals("The code resp9 is not valid for the respiration score", exception.getMessage());
    }

    @Test
    void createSofaScoreInvalidCodeNS() throws IOException {
        // copy of create-sofa-score-all-component-codes-zero, manipulated line 79
        Exception exception = executeMappingException("create-sofa-score-invalid-code-ns.json");
        assertEquals("The code ns9 is not valid for the nervous system score", exception.getMessage());
    }

    @Test
    void createSofaScoreInvalidCodeCVS() throws IOException {
        // copy of create-sofa-score-all-component-codes-zero, manipulated line 101
        Exception exception = executeMappingException("create-sofa-score-invalid-code-cvs.json");
        assertEquals("The code cvs9 is not valid for the cardiovaskular score", exception.getMessage());
    }

    @Test
    void createSofaScoreInvalidCodeLiv() throws IOException {
        // copy of create-sofa-score-all-component-codes-zero, manipulated line 123
        Exception exception = executeMappingException("create-sofa-score-invalid-code-liv.json");
        assertEquals("The code liv9 is not valid for the liver score", exception.getMessage());
    }

    @Test
    void createSofaScoreInvalidCodeCoa() throws IOException {
        // copy of create-sofa-score-all-component-codes-zero, manipulated line 145
        Exception exception = executeMappingException("create-sofa-score-invalid-code-coa.json");
        assertEquals("The code coa9 is not valid for the blood clotting score", exception.getMessage());
    }

    @Test
    void createSofaScoreInvalidCodeKid() throws IOException {
        // copy of create-sofa-score-all-component-codes-zero, manipulated line 167
        Exception exception = executeMappingException("create-sofa-score-invalid-code-kid.json");
        assertEquals("The code kid9 is not valid for the kidney score", exception.getMessage());
    }

    // #####################################################################################
    // default

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
        return assertThrows(Exception.class, () -> converter.convert(observation));
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
