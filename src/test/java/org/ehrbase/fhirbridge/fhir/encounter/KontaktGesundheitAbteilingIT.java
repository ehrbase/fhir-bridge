package org.ehrbase.fhirbridge.fhir.encounter;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.PatientenaufenthaltComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.*;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt.PatientenAufenthaltCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.validator.BloodGasPanelBundleValidator;
import org.ehrbase.fhirbridge.fhir.encounter.validator.KDSEncounterValidator;
import org.hl7.fhir.r4.model.Bundle;
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
 * Integration tests for {@link org.hl7.fhir.r4.model.Encounter Encounter} resource.
 */
public class KontaktGesundheitAbteilingIT extends AbstractMappingTestSetupIT {

    public KontaktGesundheitAbteilingIT() {
        super("Encounter/", Encounter.class);
    }

 /*   @Test
    void mappingNormalKDS() throws IOException {
        testMapping("create-kds-encounter.json",
                "paragon-create-kds-encounter.json");
    }*/

    // #####################################################################################
    // check exceptions
    @Test
    void createInvalidKontaktEbeneCodeSystem() throws IOException {
        Exception exception = executeMappingException("create-patienten-aufenthalt-kontakt-ebene-code-system-invalid.json");
        assertEquals("Invalid Code system modul-fall/CodeSystem/Kontaktebene valid code system: https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Kontaktebene", exception.getMessage());
    }

    @Test
    void createInvalidKontaktEbeneCode() throws IOException {
        Exception exception = executeMappingException("create-patienten-aufenthalt-kontakt-ebene-code-invalid.json");
        assertEquals("Invalid Code abteilung or Code System as 'Kontaktebene', valid codes are einrichtungskontakt, abteilungskontakt, versorgungsstellenkontakt.", exception.getMessage());
    }

    @Test
    void createInvalidLocationType() throws IOException {
        Exception exception = executeMappingException("create-patienten-aufenthalt-location-type-invalid.json");
        assertEquals("unexpected location physical type YY by EHR composition.", exception.getMessage());
    }

    @Test
    void createInvalidFachabteilungsSchluessel() throws IOException {
        Exception exception = executeMappingException("create-patienten-aufenthalt-fachabteilungs-schluessel-invalid.json");
        assertEquals("Invalid Code XXXX or Code System for 'Fachabteilungsschlüssel'.", exception.getMessage());
    }

    // #####################################################################################
    // default
    @Override
    public Exception executeMappingException(String path) throws IOException {
        Encounter encounter = (Encounter) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new PatientenAufenthaltCompositionConverter().convert(encounter);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Encounter encounter = (Encounter) super.testFileLoader.loadResource(resourcePath);
        PatientenAufenthaltCompositionConverter patientenAufenthaltCompositionConverter = new PatientenAufenthaltCompositionConverter();
        PatientenaufenthaltComposition mapped = patientenAufenthaltCompositionConverter.convert(encounter);
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
                .registerValueObject(new ValueObjectDefinition(PatientenaufenthaltComposition.class, List.of("location",  "feederAudit")))
                .registerValueObject(AbteilungsfallCluster.class)
                .registerValueObject(DetailsZumBettCluster.class)
                .registerValueObject(FachabteilungsschluesselDefiningCode.class)
                .registerValueObject(FachlicheOrganisationseinheitCluster.class)
                .registerValueObject(StandortCluster.class)
                .registerValueObject(VersorgungsaufenthaltAdminEntry.class)
                .registerValueObject(VersorgungsfallCluster.class)
                .registerValueObject(VersorgungstellenkontaktCluster.class)
                .build();
    }
}