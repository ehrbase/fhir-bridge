package org.ehrbase.fhirbridge.fhir.bundle;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel.GECCOSerologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight.BodyHeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.GECCOSerologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundJedesEreignisIntervalEvent;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
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

public class SerologischerBefundIT  extends AbstractMappingTestSetupIT {

    public SerologischerBefundIT() {
        super("Bundle/SerologischerBefund/", Observation.class);
    }

    @Test
    void create() throws IOException {
        create("create-anti-body-panel.json");
    }
    // #####################################################################################
    // check payload

    @Test
    void mappingNormal() throws IOException {
        testMapping("create-body-height-normal.json",
                "paragon-body-height-normal.json");
    }



    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () ->
                new GECCOSerologischerBefundCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        GECCOSerologischerBefundCompositionConverter geccoSerologischerBefundCompositionConverter = new GECCOSerologischerBefundCompositionConverter();
        GECCOSerologischerBefundComposition mapped = geccoSerologischerBefundCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOSerologischerBefundComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(BefundJedesEreignisPointEvent.class)
                .build();
    }

}
