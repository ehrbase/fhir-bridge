package org.ehrbase.fhirbridge.fhir.questionnaireresponse;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.KCCQ12CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.UCCAppFragebogenDatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
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

public class KCCQ12IT extends AbstractMappingTestSetupIT {

    public KCCQ12IT() {
        super("QuestionnaireResponse/KCCQ12/", QuestionnaireResponse.class);
    }

    @Test
    void creatKCCQ12() throws IOException {
        create("create-kccq12.json");
    }

    @Test
    void createMappingKCCQ12() throws IOException {
        testMapping("create-kccq12.json", "paragon-create-kccq12.json");
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        QuestionnaireResponse questionnaireResponse = (QuestionnaireResponse) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> new D4lQuestionnaireCompositionConverter().convert(questionnaireResponse));
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        QuestionnaireResponse resource = (QuestionnaireResponse) super.testFileLoader.loadResource(resourcePath);
        KCCQ12CompositionConverter kccq12CompositionConverter = new KCCQ12CompositionConverter();
        UCCAppFragebogenDatenComposition mappedKCCQ12 = kccq12CompositionConverter.convert(resource);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedKCCQ12);
        assertEquals(0, diff.getChanges().size());
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(UCCAppFragebogenDatenComposition.class, List.of("location", "feederAudit")))
                .registerValueObject((GesamtergebnisObservation.class))
                .registerValueObject((GesamtstatusCluster.class))
                .registerValueObject((Frage1Cluster.class))
                .registerValueObject((Frage1aCluster.class))
                .registerValueObject((Frage1bCluster.class))
                .registerValueObject((Frage1cCluster.class))
                .registerValueObject((Frage2Cluster.class))
                .registerValueObject((Frage3Cluster.class))
                .registerValueObject((Frage4Cluster.class))
                .registerValueObject((Frage5Cluster.class))
                .registerValueObject((Frage6Cluster.class))
                .registerValueObject((Frage7Cluster.class))
                .registerValueObject((Frage8Cluster.class))
                .registerValueObject((Frage8aCluster.class))
                .registerValueObject((Frage8bCluster.class))
                .registerValueObject((Frage8cCluster.class))
                .build();
    }
}
