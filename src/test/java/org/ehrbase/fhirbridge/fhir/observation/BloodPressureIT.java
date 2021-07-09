package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodpressure.BloodPressureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty.ClinicalFrailtyScaleScoreCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
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

    @Test
    void testCreateBloodPressure() throws IOException {
        testMapping("create-blood-pressure.json", "paragon-create-blood-pressure.json");
    }

    @Test
    void testLOINCDatetime() throws IOException {
        testMapping("create-blood-pressure_loinc-datetime.json", "paragon-create-blood-pressure_loinc-datetime.json");
    }

    @Test
    void testLOINCPeriod() throws IOException {
        testMapping("create-blood-pressure_loinc-period.json", "paragon-create-blood-pressure_loinc-period.json");
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(BlutdruckComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(BlutdruckObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () ->
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
