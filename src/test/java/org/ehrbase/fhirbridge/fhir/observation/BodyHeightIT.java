package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight.BodyHeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.KoerpergroesseKategorieElement;
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

class BodyHeightIT extends AbstractMappingTestSetupIT {

    public BodyHeightIT() {
        super("Observation/BodyHeight/", Observation.class); //fhir-Resource
    }

    @Test
    void createBodyHeight() throws IOException {
        create("create-body-height.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void testBodyHeightMagnitudeMin() throws IOException {
        testMapping("create-body-height_magnitude-min.json",
                "paragon-body-height_magnitude-min.json");
    }

    @Test
    void testBodyHeightMagnitudeMax() throws IOException {
        testMapping("create-body-height_magnitude-max.json",
                "paragon-body-height_magnitude-max.json");
    }
    
    @Test
    void mappingDataAbsent() throws IOException {
        testMapping("create-body-height_data-absent.json",
                "paragon-create-body-height_data-absent.json");
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KoerpergroesseComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(GroesseLaengeObservation.class)
                .registerValueObject(KoerpergroesseKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () ->
                new BodyHeightCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KoerpergroesseComposition mapped = bodyHeightCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
