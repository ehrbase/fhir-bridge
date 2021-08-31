package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.fio2.FiO2CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientinicu.PatientInIcuCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeatmungswerteKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeraetObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
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

public class FioIT extends AbstractMappingTestSetupIT {

    public FioIT() {
        super("Observation/Fio/", Observation.class); //fhir-Resource
    }

    @Test
    void createFio() throws IOException {
        create("create-fio2.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void testFioMagnitudeMin() throws IOException {
        testMapping("create-fio2_magnitude-min.json",
                "paragon-fio2_magnitude-min.json");
    }

    @Test
    void testFioMagnitudeMax() throws IOException {
        testMapping("create-fio2_magnitude-max.json",
                "paragon-fio2_magnitude-max.json");
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(BeatmungswerteComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(BeobachtungenAmBeatmungsgeraetObservation.class)
                .registerValueObject(EingeatmeterSauerstoffCluster.class)
                .registerValueObject(BeatmungswerteKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new PatientInIcuCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        FiO2CompositionConverter fiO2CompositionConverter = new FiO2CompositionConverter();
        BeatmungswerteComposition mapped = fiO2CompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
