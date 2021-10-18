package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.*;
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

public class HistoryOfTravelIT extends AbstractMappingTestSetupIT {

    public HistoryOfTravelIT() {
        super("Observation/HistoryOfTravel/", Observation.class); //fhir-Resource
    }

    @Test
    void createHistoryOfTravel() throws IOException {
        create("create-history-of-travel-yes.json");
    }

    // #####################################################################################
    // check payload
    @Test
    void mappingSnomedCodeNo() throws IOException {
        testMapping("create-history-of-travel-no.json",
                "paragon-history-of-travel-no.json");
    }

    @Test
    void mappingSnomedCodeYesAbsent() throws IOException {
        testMapping("create-history-of-travel-yes-absent.json",
                "paragon-create-history-of-travel-yes-absent.json");
    }

    @Test
    void mappingSnomedCodeUnknown() throws IOException {
        testMapping("create-history-of-travel-unknown.json",
                "paragon-history-of-travel-unknown.json");
    }

    @Test
    void mappingSnomedCodeYes() throws IOException {
        testMapping("create-history-of-travel-yes.json",
                "paragon-history-of-travel-yes.json");
    }

    // #####################################################################################
    // check exceptions

    @Test
    void createInvalidSnomedCodeOther() throws IOException {
        Exception exception = executeMappingException("create-history-of-travel-invalid-code-other.json");
        assertEquals("'Other' and 'Not applicable' not accepted in openehr template", exception.getMessage());
    }

    @Test
    void createInvalidSnomedCodeNotApplicable() throws IOException {
        Exception exception = executeMappingException("create-history-of-travel-invalid-code-na.json");
        assertEquals("'Other' and 'Not applicable' not accepted in openehr template", exception.getMessage());
    }

    @Test
    void createInvalidSystemSnomed() throws IOException {
        // copy of yes, manipulated line 64
        Exception exception = executeMappingException("create-history-of-travel-invalid-system-snomed.json");
        assertEquals("The system is not correct. It should be 'http://snomed.info/sct', but it was 'http://loinc.org'.", exception.getMessage());
    }

    @Test
    void createInvalidSystemLoinc() throws IOException {
        // copy of yes, manipulated line 143
        Exception exception = executeMappingException("create-history-of-travel-invalid-system-loinc.json");
        assertEquals("The system is not correct. It should be 'http://loinc.org', but it was 'http://snomed.info/sct'.", exception.getMessage());
    }

    @Test
    void createInvalidCodeLoinc() throws IOException {
        // copy of yes, manipulated line 131
        Exception exception = executeMappingException("create-history-of-travel-invalid-code.json");
        assertEquals("Expected loinc-code for history of travel, but got 'http://loinc.org:94653-9' instead", exception.getMessage());
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(ReisehistorieComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(ReisehistorieAdminEntry.class)
                .registerValueObject(KeineReisehistorieEvaluation.class)
                .registerValueObject(UnbekannteReisehistorieEvaluation.class)
                .registerValueObject(ReisehistorieBestimmtesReisezielCluster.class)
                .registerValueObject(ReisehistorieKategorieElement.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new HistoryOfTravelCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        HistoryOfTravelCompositionConverter compositionConverter = new HistoryOfTravelCompositionConverter();
        ReisehistorieComposition mapped = compositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
