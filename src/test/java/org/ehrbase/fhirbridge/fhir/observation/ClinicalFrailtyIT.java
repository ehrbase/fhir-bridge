package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty.ClinicalFrailtyScaleScoreCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaKategorieElement;
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

public class ClinicalFrailtyIT extends AbstractMappingTestSetupIT {

    public ClinicalFrailtyIT() {
        super("Observation/ClinicalFrailty/", Observation.class); //fhir-Resource
    }

    @Test
    void createClinicalFrailty() throws IOException {
        create("create-clinical-frailty-score.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void testClinicalFrailtyStatusOne() throws IOException {
        testMapping("create-clinical-frailty-score_status-sehrfit.json",
                "paragon-clinical-frailty-score_status-sehrfit.json");
    }

    @Test
    void testClinicalFrailtyStatusTwo() throws IOException {
        testMapping("create-clinical-frailty-score_status-durchschnittlichaktiv.json",
                "paragon-clinical-frailty-score_status-durchschnittlichaktiv.json");
    }

    @Test
    void testClinicalFrailtyStatusThree() throws IOException {
        testMapping("create-clinical-frailty-score_status-gutzurechtkommend.json",
                "paragon-clinical-frailty-score_status-gutzurechtkommend.json");
    }

    @Test
    void testClinicalFrailtyStatusFour() throws IOException {
        testMapping("create-clinical-frailty-score_status-vulnerabel.json",
                "paragon-clinical-frailty-score_status-vulnerabel.json");
    }

    @Test
    void testClinicalFrailtyStatusFive() throws IOException {
        testMapping("create-clinical-frailty-score_status-geringgradigfrail.json",
                "paragon-clinical-frailty-score_status-geringgradigfrail.json");
    }

    @Test
    void testClinicalFrailtyStatusSix() throws IOException {
        testMapping("create-clinical-frailty-score_status-mittelgradigfrail.json",
                "paragon-clinical-frailty-score_status-mittelgradigfrail.json");
    }

    @Test
    void testClinicalFrailtyStatusSeven() throws IOException {
        testMapping("create-clinical-frailty-score_status-ausgepraegtfrail.json",
                "paragon-clinical-frailty-score_status-ausgepraegtfrail.json");
    }

    @Test
    void testClinicalFrailtyStatusEight() throws IOException {
        testMapping("create-clinical-frailty-score_status-extremfrail.json",
                "paragon-clinical-frailty-score_status-extremfrail.json");
    }

    @Test
    void testClinicalFrailtyStatusNine() throws IOException {
        testMapping("create-clinical-frailty-score_status-terminalerkrankt.json",
                "paragon-clinical-frailty-score_status-terminalerkrankt.json");
    }

    // #####################################################################################
    // check exception

    @Test
    void testClinicalFrailtyInvalidStatus() throws IOException {
        try{
            super.testFileLoader.loadResource("create-clinical-frailty-score_status-invalid.json");
        }catch (ConversionException exception){
            assertEquals("Cannot match beurteilung\"99\"", exception.getMessage());
        }
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {

        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KlinischeFrailtySkalaComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(KlinischeFrailtySkalaCfsObservation.class)
                .registerValueObject(KlinischeFrailtySkalaKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new ClinicalFrailtyScaleScoreCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        ClinicalFrailtyScaleScoreCompositionConverter compositionConverter = new ClinicalFrailtyScaleScoreCompositionConverter();
        KlinischeFrailtySkalaComposition mapped = compositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
