package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodpressure.BloodPressureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
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

public class BloodPressureIT extends AbstractMappingTestSetupIT {
    public BloodPressureIT() {
        super("Observation/BloodPressure/", Observation.class); //fhir-Resource
    }

    @Test
    void createBloodPressure() throws IOException {
        create("create-blood-pressure.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void testBloodPressureSystolicMagnitudeMin() throws IOException {
        testMapping("create-blood-pressure_systolic-magnitude-min.json",
                "paragon-blood-pressure_systolic-magnitude-min.json");
    }

    @Test
    void testBloodPressureSystolicMagnitudeMax() throws IOException {
        testMapping("create-blood-pressure_systolic-magnitude-max.json",
                "paragon-blood-pressure_systolic-magnitude-max.json");
    }

    @Test
    void testBloodPressureDiastolicMagnitudeMin() throws IOException {
        testMapping("create-blood-pressure_diastolic-magnitude-min.json",
                "paragon-blood-pressure_diastolic-magnitude-min.json");
    }

    @Test
    void testBloodPressureDiastolicMagnitudeMax() throws IOException {
        testMapping("create-blood-pressure_diastolic-magnitude-max.json",
                "paragon-blood-pressure_diastolic-magnitude-max.json");
    }

    @Test
    void testAbsent() throws IOException {
        testMapping("create-blood-pressure-absent.json", "paragon-create-blood-pressure-absent.json");
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(BlutdruckComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(BlutdruckObservation.class)
                .registerValueObject(BlutdruckKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new BloodPressureCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        BloodPressureCompositionConverter compositionConverter = new BloodPressureCompositionConverter();
        BlutdruckComposition mapped = compositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }

}
