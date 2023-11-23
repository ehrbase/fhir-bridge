package org.ehrbase.fhirbridge.fhir.condition;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdsdiagnose.KDSDiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.GECCODiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AusgeschlosseneDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.KDSDiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Patient;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link Condition Condition} resource.
 */
class KDSDiagnoseIT extends AbstractMappingTestSetupIT {

    public KDSDiagnoseIT() {
        super("Condition/KDSDiagnose/", Condition.class);
    }

    @Test
    void createDefault() throws IOException {
        create("create-diagnose.json");
    }

    @Test
    void createDiagnose() throws IOException {
        testMapping("create-diagnose.json", "paragon-create-diagnose.json");
    }

    @Test
    void creatMehrfachcoding() throws IOException {
        testMapping("create-diagnose-merhfachcoding.json", "paragon-create-diagnose-mehrfachcoding.json");
    }


    @Override
    public Exception executeMappingException(String path) throws IOException {
        Condition condition = (Condition) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            (new KDSDiagnoseCompositionConverter()).convert(condition);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Condition resource = (Condition) super.testFileLoader.loadResource(resourcePath);
        KDSDiagnoseCompositionConverter compositionConverter = new KDSDiagnoseCompositionConverter();
        KDSDiagnoseComposition composition = compositionConverter.convert(resource);
        Diff diff = compareCompositions(getJavers(), paragonPath, composition);
        assertEquals(diff.getChanges().size(), 0);
    }
    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KDSDiagnoseComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(FallidentifikationCluster.class)
                .registerValueObject(ProblemDiagnoseAttributCluster.class)
                .registerValueObject(PrimaercodeEvaluation.class)
                .registerValueObject(PrimaercodeEvaluation.class)
                .registerValueObject(SekundaercodeEvaluation.class)
                .registerValueObject(LebensphaseCluster.class)
                .registerValueObject(MehrfachkodierungskennzeichenIcd10GmCluster.class)
                .registerValueObject(KlinischerStatusCluster.class)
                .build();
    }
}
