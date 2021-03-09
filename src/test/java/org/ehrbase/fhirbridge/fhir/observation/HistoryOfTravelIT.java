package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.historyoftravel.HistoryOfTravelConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.KeineReisehistorieEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieBestimmtesReisezielCluster;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.UnbekannteReisehistorieEvaluation;
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
        create("create-history-of-travel-no.json");
    }

    // #####################################################################################
    // check payload
    @Test
    void mappingNo() throws IOException {
        testMapping("create-history-of-travel-no.json",
                "paragon-history-of-travel-no.json");
    }

    @Test
    void mappingUnknown() throws IOException {
        testMapping("create-history-of-travel-unknown.json",
                "paragon-history-of-travel-unknown.json");
    }

    @Test
    void mappingYes() throws IOException {
        testMapping("create-history-of-travel-yes.json",
                "paragon-history-of-travel-yes.json");
    }

    // #####################################################################################
    // check exceptions
    @Test
    void createInvalidSystem() throws IOException {
        // copy of yes, manipulated line 143
        Exception exception = executeMappingException("create-history-of-travel-invalid-system.json");
        assertEquals("The system is not correct. It should be 'http://loinc.org', but it was 'http://snomed.info/sct'.", exception.getMessage());
    }

    @Test
    void createInvalidCode() throws IOException {
        // copy of yes, manipulated line 131
        Exception exception = executeMappingException("create-history-of-travel-invalid-code.json");
        assertEquals("Expected loinc-code for history of travel, but got 'http://loinc.org:94653-2' instead", exception.getMessage());
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
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () ->
                new HistoryOfTravelConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        HistoryOfTravelConverter compositionConverter = new HistoryOfTravelConverter();
        ReisehistorieComposition mapped = compositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
