package org.ehrbase.fhirbridge.fhir.bundle;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel.GECCOSerologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.GECCOSerologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractBundleMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.converter.AntiBodyPanelConverter;
import org.hl7.fhir.r4.model.Bundle;
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

public class SerologischerBefundIT  extends AbstractBundleMappingTestSetupIT {

    public SerologischerBefundIT() {
        super("Bundle/SerologischerBefund/", Bundle.class);
    }

    @Test
    void create() throws IOException {
        create("create-anti-body-panel.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void mappingNormal() throws IOException {
        testMapping("create-anti-body-panel.json",
                "paragon-create-anti-body-panel.json");
    }

    @Test
    void absentReason() throws IOException {
        testMapping("create-anti-body-panel-absent.json",
                "paragon-create-anti-body-panel-absent.json");
    }



    @Test
    void mappingInvalidKategorie() throws IOException {
        Exception exception =  executeMappingException("invalid-kategorie.json");
        assertEquals("Category code is not defined in anti body panel, therefore the bundle is incomplete. Please add category observation category to the panel", exception.getMessage());
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Bundle bundle = (Bundle) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new GECCOSerologischerBefundCompositionConverter().convert(new AntiBodyPanelConverter().convert(bundle));
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Bundle bundle = (Bundle) super.testFileLoader.loadResource(resourcePath);
        AntiBodyPanelConverter antiBodyPanelConverter = new AntiBodyPanelConverter();
        Observation observation = antiBodyPanelConverter.convert(bundle);
        GECCOSerologischerBefundCompositionConverter geccoSerologischerBefundCompositionConverter = new GECCOSerologischerBefundCompositionConverter();
        GECCOSerologischerBefundComposition mappedGECCOSerologischerBefundComposition = geccoSerologischerBefundCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedGECCOSerologischerBefundComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOSerologischerBefundComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(BefundJedesEreignisPointEvent.class)
                .registerValueObject(BefundObservation.class)
                .registerValueObject(GeccoSerologischerBefundKategorieLoincElement.class)
                .registerValueObject(LabortestPanelCluster.class)
                .registerValueObject(ProAnalytCluster.class)
                .registerValueObject(ProAnalytQuantitativesErgebnisDvQuantity.class)
                .build();
    }

}
