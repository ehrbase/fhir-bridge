package org.ehrbase.fhirbridge.fhir.bundle;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel.GECCOSerologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.BloodGasPanelCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten.UCCSensordatenCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.*;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.UCCAppSensorDatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractBundleMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.converter.AntiBodyPanelConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.BloodGasPanelConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.UCCSensordatenBundleConverter;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Composition;
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

public class SesordatenIT  extends AbstractBundleMappingTestSetupIT {


    public SesordatenIT() {
        super("Bundle/UCCSensordaten/", Bundle.class);
    }


    @Test
    void createUCCSensordatenVitalSigns() throws IOException {
        create("create-ucc-vitalsigns.json");
    }

    @Test
    void createMappingUCCSensordatenVitalSigns() throws IOException {
        testMapping("create-ucc-vitalsigns.json", "paragon-create-ucc-vitalsigns.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(UCCAppSensorDatenComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(PulsfrequenzHerzfrequenzObservation.class)
                .registerValueObject(MitSensorGemesseneKoerperlicheAktivitaetObservation.class)
                .registerValueObject(PulsfrequenzHerzfrequenzRuhepulsEvent.class)
                .registerValueObject(PulsfrequenzHerzfrequenzMittlereHerzfrequenzEvent.class)
                .registerValueObject(PulsfrequenzHerzfrequenzJedesEreignisPointEvent.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Bundle bundle = (Bundle) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new UCCSensordatenCompositionConverter().convert(new UCCSensordatenBundleConverter().convert(bundle));
        });    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Bundle bundle = (Bundle) super.testFileLoader.loadResource(resourcePath);
        UCCSensordatenBundleConverter uccSensordatenBundleConverter = new UCCSensordatenBundleConverter();
        Composition composition = uccSensordatenBundleConverter.convert(bundle);
        UCCSensordatenCompositionConverter uccSensordatenCompositionConverter = new UCCSensordatenCompositionConverter();
        UCCAppSensorDatenComposition uccAppSensorDatenComposition = uccSensordatenCompositionConverter.convert(composition);
        Diff diff = compareCompositions(getJavers(), paragonPath, uccAppSensorDatenComposition);
        assertEquals(diff.getChanges().size(), 0);
    }
}
