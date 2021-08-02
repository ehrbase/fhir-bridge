package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight.BodyWeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientinicu.PatientInIcuCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.KoerpergewichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
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

public class BodyWeightIT extends AbstractMappingTestSetupIT {

    public BodyWeightIT() {
        super("Observation/BodyWeight/", Observation.class); //fhir-Resource
    }

    @Test
    void createBodyWeightIT() throws IOException {
        create("create-body-weight.json");
    }

    @Test
    void mappingBodyWeight() throws IOException {
        testMapping("create-body-weight.json",
                "paragon-create-body-weight.json");
    }

    @Test
    void mappingBodyWeightLoincDateTime() throws IOException {
        testMapping("create-body-weight-loinc-datetime.json",
                "paragon-create-body-weight-loinc-datetime.json");
    }

    @Test
    void mappingBodyWeightLoincPeriod() throws IOException {
        testMapping("create-body-weight-loinc-period.json",
                "paragon-create-body-weight-loinc-period.json");
    }

    @Test
    void mappingBodyWeightLoincPeriod2() throws IOException {
        testMapping("create-body-weight-loinc-period_2.json",
                "paragon-create-body-weight-loinc-period_2.json");
    }


    @Test
    void mappingBodyWeightSnomedDateTime() throws IOException {
        testMapping("create-body-weight-snomed-datetime.json",
                "paragon-create-body-weight-snomed-datetime.json");
    }

    @Test
    void mappingBodyWeightSnomedPeriod() throws IOException {
        testMapping("create-body-weight-snomed-period.json",
                "paragon-create-body-weight-snomed-period.json");
    }

    @Test
    void mappingBodyWeightSnomedPeriod2() throws IOException {
        testMapping("create-body-weight-snomed-period_2.json",
                "paragon-create-body-weight-snomed-period_2.json");
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KoerpergewichtComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(KoerpergewichtObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new BodyWeightCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        BodyWeightCompositionConverter bodyWeightCompositionConverter = new BodyWeightCompositionConverter();
        KoerpergewichtComposition mapped = bodyWeightCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
