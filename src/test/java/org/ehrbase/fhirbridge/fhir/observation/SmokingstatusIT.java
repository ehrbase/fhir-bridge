package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus.RaucherstatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RegistereintragKategorieElement;
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

public class SmokingstatusIT extends AbstractMappingTestSetupIT {

    public SmokingstatusIT() {
        super("Observation/SmokingStatus/", Observation.class);
    }


/*   requires patient reference which requires to create a patient, integrations tests are anyways covered by the robot tests, so we leave this blank
    void createSmokingStatus() throws IOException {
        create("create-smoking-status.json");
    }*/

    @Test
    void createSmokingStatusMapping() throws IOException {
        testMapping("create-smoking-status.json","paragon-create-smoking-status.json");
    }

    @Test
    void createSmokingStatusMappingDateTime() throws IOException {
        testMapping("create-smoking-status-datetime.json","paragon-create-smoking-status-datetime.json");
    }

    @Test
    void createSmokingStatusMappingValueAbsent() throws IOException {
        testMapping("create-smoking-status-value-absent.json","paragon-create-smoking-status-value-absent.json");
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(RaucherstatusComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(RaucherstatusEvaluation.class)
                .registerValueObject(RegistereintragKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () ->
                new RaucherstatusCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        RaucherstatusCompositionConverter raucherstatusCompositionConverter = new RaucherstatusCompositionConverter();
        RaucherstatusComposition mapped = raucherstatusCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
