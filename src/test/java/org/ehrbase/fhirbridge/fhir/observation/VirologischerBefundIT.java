package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik.MibiMolekBefundConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik.MibiMolekDiagnostikConverter;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.observation.validator.MibiMolekDiagnostikValidator;
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

public class VirologischerBefundIT extends AbstractMappingTestSetupIT {

    public VirologischerBefundIT() {
        super("Observation/VirologischerBefund/", Observation.class); //fhir-Resource
    }

    @Test
    void createMREVREMapping() throws IOException {
        testMapping("create-molek-diag.json", "paraon-create-molek-diag.json");
    }

    @Test
    void molekProbe() throws IOException {
        testMapping("create-molek-diag-probe.json", "paragon-create-molek-diag-probe.json");
    }

    @Test
    void invalidMikroMissing() throws IOException {
        Exception exception = executeValidatorException("mirko_org_missing.json");
        assertEquals("NameDesMikrorganismus is missing from Molekulare Diagnostik", exception.getMessage());
    }

    @Test
    void invalidMissingCollectedProbe() throws IOException {
        Exception exception = executeValidatorException("missing-collected.json");
        assertEquals("Specimen is missing collection collected time.", exception.getMessage());
    }

    @Test
    void invalidMissingCollectionProbe() throws IOException {
        Exception exception = executeValidatorException("missing-collection.json");
        assertEquals("Specimen is missing collection", exception.getMessage());
    }

    @Test
    void invalidMissingValue() throws IOException {
        Exception exception = executeValidatorException("missing-value.json");
        assertEquals("Molekulare Diagnostik has to include a ValueCodeableConcept.", exception.getMessage());
    }

    @Test
    void invalidWrongStatus() throws IOException {
        Exception exception = executeValidatorException("wrong-status.json");
        assertEquals("For status in Molekulare Diagnostik only final is allowed", exception.getMessage());
    }

    @Test
    void invalidEncounterMissing() throws IOException {
        Exception exception = executeValidatorException("encounter-missing.json");
        assertEquals("Encounter is missing", exception.getMessage());
    }

    @Test
    void invalidEncounterIdentifierMissing() throws IOException {
        Exception exception = executeValidatorException("encounter-identifier-missing.json");
        assertEquals("Encounter is missing identifier", exception.getMessage());
    }

    @Test
    void invalidEncounterIdentifierValue() throws IOException {
        Exception exception = executeValidatorException("encounter-identifier-value-missing.json");
        assertEquals("Encounter is missing identifier.system and/or identifier.value", exception.getMessage());
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(VirologischerBefundComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(FallidentifikationCluster.class)
                .registerValueObject(BefundObservation.class)
                .registerValueObject(EmpfaengerstandortCluster.class)
                .registerValueObject(new ValueObjectDefinition(BefundJedesEreignisPointEvent.class, List.of("timeValue")))
                .registerValueObject(ProbeCluster.class)
                .registerValueObject(LabortestPanelCluster.class)
                .registerValueObject(ProAnalytCluster.class)
                .registerValueObject(ProAnalytQuantitativesErgebnisElement.class)
                .registerValueObject(ProAnalytErgebnisStatusElement.class)
                .registerValueObject(ProAnalytQuantitativesErgebnisDvQuantity.class)
                .registerValueObject(ProAnalytErgebnisStatusDvCodedText.class)
                .registerValueObject(AnatomischeLokalisationCluster.class)
                .build();
    }

    public Exception executeValidatorException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new MibiMolekDiagnostikValidator().validateRequest(observation, null);
        });
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new MibiMolekBefundConverter().convert((observation))
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        MibiMolekDiagnostikConverter mibiMolekDiagnostikConverter = new MibiMolekDiagnostikConverter();
        VirologischerBefundComposition mapped = mibiMolekDiagnostikConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
