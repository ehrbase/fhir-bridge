package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.BodyHeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
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
        Observation observation = (Observation) super.testFileLoader.loadResource("create-body-height-normal.json");//fhir-beispiel
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff =  compareCompositions(getJavers(), "paragon-body-height-normal.json", mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void mapping_loinc_datetime() throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource("create-body-height-loinc-datetime.json");
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff =  compareCompositions(getJavers(), "paragon-body-height-loinc-datetime.json", mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void mapping_loinc_period() throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource("create-body-height-loinc-period.json");
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff =  compareCompositions(getJavers(), "paragon-body-height-loinc-period.json", mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void mapping_loinc_period_2() throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource("create-body-height-loinc-period_2.json");
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff =  compareCompositions(getJavers(), "paragon-body-height-loinc-period_2.json", mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void mapping_snomed_datetime() throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource("create-body-height-snomed-datetime.json");
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff =  compareCompositions(getJavers(), "paragon-body-height-snomed-datetime.json", mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void mapping_snomed_period() throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource("create-body-height-snomed-period.json");
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff =  compareCompositions(getJavers(), "paragon-body-height-snomed-period.json", mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void mapping_snomed_period_2() throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource("create-body-height-snomed-period_2.json");
        BodyHeightCompositionConverter bodyHeightCompositionConverter = new BodyHeightCompositionConverter();
        KorpergrosseComposition mapped = bodyHeightCompositionConverter.toComposition(observation);
        Diff diff =  compareCompositions(getJavers(), "paragon-body-height-snomed-period_2.json", mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Test
    void createInvalidBefund() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-body-height-loinc-datetime_invalid.json"));
        assertEquals("No time is set", exception.getMessage());
    }

    // #####################################################################################
    // check exceptions


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
    public Exception executeMappingUnprocessableEntityException(IBaseResource bodyHeightResource) {
        return assertThrows(UnprocessableEntityException.class, () -> {
            new BodyHeightCompositionConverter().toComposition((Observation) bodyHeightResource);
        });
    }

}
