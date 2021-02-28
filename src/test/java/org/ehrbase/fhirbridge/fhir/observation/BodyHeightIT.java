package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.BodyHeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund.RadiologischerBefundConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.DiagnosticReport;
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

public class BodyHeightIT extends AbstractMappingTestSetupIT {

    public BodyHeightIT() {
        super("Observation/BodyHeight/", Observation.class); //fhir-Resource
    }

    @Test
    void createBodyHeightLoincPeriod() throws IOException {
        create("create-body-height-loinc-period.json");
    }

    @Test
    void createBodyHeightSnomedPeriod() throws IOException {
        create("create-body-height-snomed-period.json");
    }

    // #####################################################################################
    // check payload
    @Test
    void mapping_normal() throws IOException {
        testMapping("create-body-height-normal.json",
                "paragon-body-height-normal.json");
    }

    @Test
    void mapping_loinc_datetime() throws IOException {
        testMapping("create-body-height-loinc-datetime.json",
                "paragon-body-height-loinc-datetime.json");
    }

    @Test
    void mapping_loinc_period() throws IOException {
        testMapping("create-body-height-loinc-period.json",
                "paragon-body-height-loinc-period.json");
    }

    @Test
    void mapping_loinc_period_2() throws IOException {
        testMapping("create-body-height-loinc-period_2.json",
                "paragon-body-height-loinc-period_2.json");
    }

    @Test
    void mapping_snomed_datetime() throws IOException {
        testMapping("create-body-height-snomed-datetime.json",
                "paragon-body-height-snomed-datetime.json");
    }

    @Test
    void mapping_snomed_period() throws IOException {
        testMapping("create-body-height-snomed-period.json",
                "paragon-body-height-snomed-period.json");
    }

    @Test
    void mapping_snomed_period_2() throws IOException {
        testMapping("create-body-height-snomed-period_2.json",
                "paragon-body-height-snomed-period_2.json");
    }


    // #####################################################################################
    // check exceptions
    @Test
    void createInvalidBefund() throws IOException {
        Exception exception = executeMappingException("create-body-height-loinc-datetime_invalid.json");
        assertEquals("No time is set", exception.getMessage());
    }


    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KorpergrosseComposition.class, List.of("location")))
                .registerValueObject(GrosseLangeObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () -> {
            new BodyHeightCompositionConverter().toComposition(obs);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
