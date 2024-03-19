package org.ehrbase.fhirbridge.fhir.encounter;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdsfall.SonstigerPatientenAufenthaltConverter;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.SonstigerVersorgungsfallComposition;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition.VersorgungsaufenthaltAdminEntry;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.encounter.validator.KDSEncounterValidator;
import org.hl7.fhir.r4.model.Encounter;
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

/**
 * Integration tests for {@link Encounter Encounter} resource.
 */
public class KdsFall extends AbstractMappingTestSetupIT {

    public KdsFall() {
        super("Encounter/kdsfall/", Encounter.class);
    }

    @Test
    void mappingNormalKDS() throws IOException {
        testMapping("create-kds-encounter.json",
                "paragon-create-kds-encounter.json");
    }

    // #####################################################################################
    @Test
    void createInvalidStatus() throws IOException {
        Exception exception = executeValidatorException("invalid-status.json");
        assertEquals("Encounter status has to be finished !", exception.getMessage());
    }

    @Test
    void invalidServiceType() throws IOException {
        Exception exception = executeValidatorException("invalid-status.json");
        assertEquals("Encounter status has to be finished !", exception.getMessage());
    }

    @Test
    void invalidIdentifierMissing() throws IOException {
        Exception exception = executeValidatorException("identifier-missing.json");
        assertEquals("Encounter identifier missing!", exception.getMessage());
    }
    // #####################################################################################
    // default
    @Override
    public Exception executeMappingException(String path) throws IOException {
        Encounter encounter = (Encounter) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new SonstigerPatientenAufenthaltConverter().convert(encounter);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Encounter encounter = (Encounter) super.testFileLoader.loadResource(resourcePath);
        SonstigerPatientenAufenthaltConverter sonstigerPatientenAufenthaltConverter = new SonstigerPatientenAufenthaltConverter();
        SonstigerVersorgungsfallComposition mapped = sonstigerPatientenAufenthaltConverter.convert(encounter);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }

    public Exception executeValidatorException(String path) throws IOException {
        Encounter encounter = (Encounter) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new KDSEncounterValidator().validateRequest(encounter, null);
        });
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(SonstigerVersorgungsfallComposition.class, List.of("location",  "feederAudit")))
                .registerValueObject(VersorgungsaufenthaltAdminEntry.class)
                .registerValueObject(FachlicheOrganisationseinheitCluster.class)
                .build();
    }
}