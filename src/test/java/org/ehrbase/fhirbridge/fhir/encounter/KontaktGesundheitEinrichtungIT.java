package org.ehrbase.fhirbridge.fhir.encounter;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.StationaererVersorgungsfallComposition;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.*;
import org.ehrbase.fhirbridge.ehr.converter.specific.stationaererversorgungsfall.StationaererVersorgungsfallCompositionConverter;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
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
public class KontaktGesundheitEinrichtungIT extends AbstractMappingTestSetupIT {

    public KontaktGesundheitEinrichtungIT() {
        super("Encounter/", Encounter.class);
    }

    @Test
    void createStationaerVersorgungsfallNormal() throws IOException {
        create("create-stationaer-versorgungsfall.json");
    }

    // #####################################################################################
    // check payload
    @Test
    void mappingNormal() throws IOException {
        testMapping("create-stationaer-versorgungsfall.json",
                "paragon-stat-versorgungsfall.json");
    }

    // #####################################################################################
    // check exceptions
    @Test
    void createInvalidAufnahmegrundCode() throws IOException {
        Exception exception = executeMappingException("create-stat-versorgungsfall-aufnahmegrund-invalid.json");
        assertEquals("Invalid Code 25 or Code System for mapping of 'Aufnahmegrund', valid codes are: 01, 02, 03, 04, 05, 06, 07, 08, 10.", exception.getMessage());
    }

    @Test
    void createInvalidAufnahmeanlassCode() throws IOException {
        Exception exception = executeMappingException("create-stat-versorgungsfall-aufnahmeanlass-invalid.json");
        assertEquals("Invalid Code 25 or Code System for mapping of 'Aufnahmeanlass', valid codes are: N, G, E, A, V, Z, B, R.", exception.getMessage());
    }

    @Test
    void createInvalidEntlassungCode() throws IOException {
        Exception exception = executeMappingException("create-stat-versorgungsfall-entlassung-invalid.json");
        assertEquals("Invalid Code XX or Code System for mapping of 'art der Entlassung'.", exception.getMessage());
    }

    // #####################################################################################
    // default
    @Override
    public Exception executeMappingException(String path) throws IOException {
        Encounter encounter = (Encounter) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () -> {
            new StationaererVersorgungsfallCompositionConverter().convert(encounter);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Encounter encounter = (Encounter) super.testFileLoader.loadResource(resourcePath);
        StationaererVersorgungsfallCompositionConverter stationaererVersorgungsfallCompositionConverter = new StationaererVersorgungsfallCompositionConverter();
        StationaererVersorgungsfallComposition mapped = stationaererVersorgungsfallCompositionConverter.convert(encounter);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(StationaererVersorgungsfallComposition.class, List.of("location",  "feederAudit")))
                .registerValueObject(ArtDerEntlassungDefiningCode.class)
                .registerValueObject(AufnahmeanlassDefiningCode.class)
                .registerValueObject(AufnahmedatenAdminEntry.class)
                .registerValueObject(AufnahmegrundDefiningCode.class)
                .registerValueObject(EntlassungsdatenAdminEntry.class)
                .registerValueObject(FachlicheOrganisationseinheitCluster.class)
                .registerValueObject(FallstatusDefiningCode.class)
                .registerValueObject(KlinischerZustandDesPatientenDefiningCode.class)
                .registerValueObject(OrganisationsschluesselDefiningCode.class)
                .registerValueObject(VorherigerPatientenstandortVorAufnahmeCluster.class)
                .registerValueObject(VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster.class)
                .registerValueObject(ZugewiesenerStandortBeiEntlassungCluster.class)
                .registerValueObject(ZugewieseneVerantwortlicheOrganisationseinheitBeiEntlassungCluster.class)
                .build();
    }

}