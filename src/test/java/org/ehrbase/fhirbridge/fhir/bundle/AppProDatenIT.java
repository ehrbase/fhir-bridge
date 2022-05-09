package org.ehrbase.fhirbridge.fhir.bundle;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten.UCCAppProDatenConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.UCCSensordatenCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.UCCAppPRODatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.*;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.UCCAppSensorDatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractBundleMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.converter.UCCAppProDatenBundleConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.UCCSensordatenVitalSignsBundleConverter;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Composition;
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

public class AppProDatenIT extends AbstractBundleMappingTestSetupIT {


    public AppProDatenIT() {
        super("Bundle/UCCAppPro/", Bundle.class);
    }


    @Test
    void createAppProDaten() throws IOException {
        create("create-app-pro.json");
    }

    @Test
    void createMappingAppProDaten() throws IOException {
        testMapping("create-app-pro.json", "paragon-create-app-pro.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(UCCAppPRODatenComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(BloodPressureObservation.class)
                .registerValueObject(BodyWeightObservation.class)
                .registerValueObject(PulseHeartBeatObservation.class)
                .registerValueObject(BloodPressure24HourAverageIntervalEvent.class)
                .registerValueObject(BloodPressureAnyEventPointEvent.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Bundle bundle = (Bundle) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new UCCAppProDatenConverter().convert(new UCCAppProDatenBundleConverter().convert(bundle));
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Bundle bundle = (Bundle) super.testFileLoader.loadResource(resourcePath);
        UCCAppProDatenBundleConverter appProDatenBundleConverter = new UCCAppProDatenBundleConverter();
        Composition composition = appProDatenBundleConverter.convert(bundle);
        UCCAppProDatenConverter uccAppProDatenConverter = new UCCAppProDatenConverter();
        UCCAppPRODatenComposition uccAppPRODatenComposition = uccAppProDatenConverter.convert(composition);
        Diff diff = compareCompositions(getJavers(), paragonPath, uccAppPRODatenComposition);
        assertEquals(diff.getChanges().size(), 0);
    }
}
