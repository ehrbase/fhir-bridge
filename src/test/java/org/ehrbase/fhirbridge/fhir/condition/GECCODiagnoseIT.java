package org.ehrbase.fhirbridge.fhir.condition;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.GECCODiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Condition;
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
 * Integration tests for {@link org.hl7.fhir.r4.model.Condition Condition} resource.
 */
class GECCODiagnoseIT extends AbstractMappingTestSetupIT {

    public GECCODiagnoseIT() {
        super("Condition/GECCODiagnose/", Condition.class);
    }

    @Test
    void createChronicLiverDisease() throws IOException {
        create("create-chronic-liver-disease.json");
    }

    @Test
    void createMappingChronicLungDisease() throws IOException {
        testMapping("create-chronic-lung-disease.json", "paragon-create-chronic-lung-disease.json");

    }

    @Test
    void createMappingDiabetesMellitus() throws IOException {
        testMapping("create-diabetes-mellitus.json", "paragon-create-diabetes-mellitus.json");

    }

    @Test
    void createMappingMalignantNeoplasticDisease() throws IOException {
        testMapping("create-malignant-neoplastic-disease-absent.json", "paragon-create-malignant-neoplastic-disease-absent.json");

    }

    @Test
    void createMappingRheumatologicalImmunologicalDiseases() throws IOException {
        testMapping("create-rheumatological-immunological-diseases-rheumatism.json", "paragon-create-rheumatological-immunological-diseases-rheumatism.json");

    }

    @Test
    void createMappingHIV() throws IOException {
        testMapping("create-human-immunodeficiency-virus-infection1.json", "paragon-create-human-immunodeficiency-virus-infection1.json");

    }

    @Test
    void createMappingCardiovascularDiseases() throws IOException {
        testMapping("create-cardiovascular-diseases.json", "paragon-create-cardiovascular-diseases.json");


    }

    @Test
    void createMappingChronicKidneyDisease() throws IOException {
        testMapping("create-chronic-kidney-diseases2.json", "paragon-create-chronic-kidney-diseases2.json");

    }

    @Test
    void createMappingChronicNeurologicalMentalDiseases() throws IOException {
        testMapping("create-chronic-neurological-mental-diseases.json", "paragon-create-chronic-neurological-mental-diseases.json");

    }

    @Test
    void createMappingComplicationsCovid19() throws IOException {
        testMapping("create-complications-covid19-0.json", "paragon-create-complications-covid19-0.json");

    }

    @Test
    void createMappingOrganRecipient() throws IOException {
        testMapping("create-organ-recipient.json", "paragon-create-organ-recipient.json");

    }

    @Test
    void createMappingGastrointerstinalUlcers() throws IOException {
        testMapping("create-gastrointestinal-ulcers.json", "paragon-create-gastrointestinal-ulcers.json");
    }

    @Test
    void createMappingDependenceOnVentilator() throws IOException {
        testMapping("create-dependence-on-ventilator.json", "paragon-create-dependence-on-ventilator.json");
    }


    @Test
    void createMappingCreateChronicLiverDisease() throws IOException {
        testMapping("create-chronic-liver-disease.json", "paragon-create-chronic-liver-disease.json");
    }


    @Test
    void createDiagnoseInvalidVerificationStatus() throws IOException {
        Exception exception = executeMappingException("invalid/invalid-verification-status.json");
        assertEquals("SNOMED code is invalid in VerificationStatus.coding.code", exception.getMessage());
    }

    @Test
    void createDiagnoseInvalidKategorie() throws IOException {
        Exception exception = executeMappingException("invalid/invalid-kategorie.json");
        assertEquals("Category has either no or an unsupported SNOMED code", exception.getMessage());
    }

    @Test
    void createDiagnoseInvalidBodySite() throws IOException {
        Exception exception = executeMappingException("invalid/invalid-body-site.json");
        assertEquals("Bodysite contains either a wrong code or code system.", exception.getMessage());
    }

    @Test
    void createDiagnoseInvalidSeverity() throws IOException {
        Exception exception = executeMappingException("invalid/invalid-severity.json");
        assertEquals("Severity contains either a wrong code or code system.", exception.getMessage());
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Condition condition = (Condition) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            (new GECCODiagnoseCompositionConverter()).convert(condition);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Condition resource = (Condition) super.testFileLoader.loadResource(resourcePath);
        GECCODiagnoseCompositionConverter compositionConverter = new GECCODiagnoseCompositionConverter();
        GECCODiagnoseComposition composition = compositionConverter.convert(resource);
        Diff diff = compareCompositions(getJavers(), paragonPath, composition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCODiagnoseComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(AusgeschlosseneDiagnoseEvaluation.class)
                .registerValueObject(VorliegendeDiagnoseEvaluation.class)
                .registerValueObject(UnbekannteDiagnoseEvaluation.class)
                .registerValueObject(VorliegendeDiagnoseNameDesProblemsDerDiagnoseDvCodedText.class)
                .registerValueObject(KoerperstelleCluster.class)
                .build();
    }
}
