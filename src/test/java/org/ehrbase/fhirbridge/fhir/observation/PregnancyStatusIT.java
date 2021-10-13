package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus.PregnancyStatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.SchwangerschaftsstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusObservation;
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

public class PregnancyStatusIT extends AbstractMappingTestSetupIT {

    public PregnancyStatusIT() {
        super("Observation/PregnancyStatus/", Observation.class); //fhir-Resource
    }

    @Test
    void createPregnancyStatus() throws IOException {
        create("create-pregnancy-status.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void testPregnancyStatusLoincPregnant() throws IOException {
        testMapping("create-pregnancy-status-loinc-pregnant.json",
                "paragon-pregnancy-status-loinc-pregnant.json");
    }

    @Test
    void testPregnancyStatusLoincNotPregnant() throws IOException {
        testMapping("create-pregnancy-status-loinc-not-pregnant.json",
                "paragon-pregnancy-status-loinc-not-pregnant.json");
    }

    @Test
    void testPregnancyStatusLoincUnknown() throws IOException {
        testMapping("create-pregnancy-status-loinc-unknown.json",
                "paragon-pregnancy-status-loinc-unknown.json");
    }

    @Test
    void testPregnancyStatusLoincAndSnomedPregnant() throws IOException {
        testMapping("create-pregnancy-status-loinc-snomed-pregnant.json",
                "paragon-pregnancy-status-loinc-snomed-pregnant.json");
    }

    @Test
    void testPregnancyStatusLoincAndSnomedNotPregnant() throws IOException {
        testMapping("create-pregnancy-status-loinc-snomed-not-pregnant.json",
                "paragon-pregnancy-status-loinc-snomed-not-pregnant.json");
    }

    @Test
    void testPregnancyStatusLoincAndSnomedUnknown() throws IOException {
        testMapping("create-pregnancy-status-loinc-snomed-unknown.json",
                "paragon-pregnancy-status-loinc-snomed-unknown.json");
    }

    @Test
    void testPregnancyStatusAbsent() throws IOException {
        testMapping("create-pregnancy-status-absent.json",
                "paragon-pregnancy-status-absent.json");
    }

    // #####################################################################################
    // check exception

    @Test
    void testPregnancyStatusInvalidLoinc() throws IOException {
        // copy of create-pregnancy-status-loinc-pregnant.json, manipulated line 48
        Exception exception = executeMappingException("create-pregnancy-status-invalid-loinc.json");
        assertEquals("Status code LA99999-6 is not supported", exception.getMessage());
    }

    @Test
    void testPregnancyStatusInvalidSnomed() throws IOException {
        // copy of create-pregnancy-status-loinc-snomed-pregnant.json, manipulated line 53 and LOINC and SNOMED blocks swapped
        Exception exception = executeMappingException("create-pregnancy-status-invalid-snomed.json");
        assertEquals("Status code 99999999 is not supported", exception.getMessage());
    }

    @Test
    void testPregnancyStatusInvalidLoincAndSnomed() throws IOException {
        // copy of create-pregnancy-status-loinc-snomed-pregnant.json, manipulated lines 48 and 53 (throws invalid LOINC)
        Exception exception = executeMappingException("create-pregnancy-status-invalid-loinc-snomed.json");
        assertEquals("Status code LA99999-6 is not supported", exception.getMessage());
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(SchwangerschaftsstatusComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(SchwangerschaftsstatusObservation.class)
                .registerValueObject(SchwangerschaftsstatusKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new PregnancyStatusCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        PregnancyStatusCompositionConverter pregnancyStatusCompositionConverter = new PregnancyStatusCompositionConverter();
        SchwangerschaftsstatusComposition mapped = pregnancyStatusCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
