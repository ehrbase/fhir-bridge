package org.ehrbase.fhirbridge.fhir.condition;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.symptom.SymptomCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomAussageUeberDieFehlendeInformationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomAnatomischeLokalisationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
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

class SymptomIT extends AbstractMappingTestSetupIT {

    public SymptomIT() {
        super("Condition/Symptom/", Condition.class);
    }

    @Test
    void createSymptomCovid19() throws IOException {
        create("create-symptoms-covid-19-present.json");
    }

    @Test
    void createSymptomCovid19AbsentMapping() throws IOException {
        testMapping("create-symptoms-covid-19-absent.json", "paragon-create-symptoms-covid-19-absent.json");
    }

    @Test
    void createSymptomCovid19PresentMapping() throws IOException {
        testMapping("create-symptoms-covid-19-present.json", "paragon-create-symptoms-covid-19-present.json");

    }

    @Test
    void createSymptomCovid19UnknownIdMapping() throws IOException {
        testMapping("create-symptoms-covid-19-unknown.json", "paragon-create-symptoms-covid-19-unknown.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(SymptomComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(VorliegendesSymptomObservation.class)
                .registerValueObject(AusgeschlossenesSymptomEvaluation.class)
                .registerValueObject(UnbekanntesSymptomEvaluation.class)
                .registerValueObject(VorliegendesSymptomAnatomischeLokalisationElement.class)
                .registerValueObject(UnbekanntesSymptomAussageUeberDieFehlendeInformationElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Condition condition = (Condition) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new SymptomCompositionConverter().convert(condition)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Condition condition = (Condition) testFileLoader.loadResource(resourcePath);
        SymptomCompositionConverter compositionConverter = new SymptomCompositionConverter();
        SymptomComposition mapped = compositionConverter.convert(condition);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
