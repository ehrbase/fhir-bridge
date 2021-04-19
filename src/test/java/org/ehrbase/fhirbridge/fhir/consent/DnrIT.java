package org.ehrbase.fhirbridge.fhir.consent;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.DnrAnordnung.DnrAnordnungCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.DNRAnordnungComposition;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Consent;
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

class DnrIT extends AbstractMappingTestSetupIT {

    public DnrIT() {
        super("Consent/", Consent.class); //fhir-Resource
    }


    // #####################################################################################
    // check payload
    @Test
    void mappingNormal() throws IOException {
        testMapping("consent-example.json",
                "paragon-consent-dnr-normal.json");
    }


    // #####################################################################################
    // check exceptions
    @Test
    void createInvalidDnr() throws IOException {
        Exception exception = executeMappingException("consent-example-invalid-status.json");
        assertEquals("Oh noes, this should never happen", exception.getMessage());
    }


    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(DNRAnordnungComposition.class, List.of("location", "feederAudit")))
                //.registerValueObject(GroesseLaengeObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Consent csnt = (Consent) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () ->
                new DnrAnordnungCompositionConverter().convert(csnt)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Consent consent = (Consent) super.testFileLoader.loadResource(resourcePath);
        DnrAnordnungCompositionConverter dnrAnordnungCompositionConverter = new DnrAnordnungCompositionConverter();
        DNRAnordnungComposition mapped = dnrAnordnungCompositionConverter.convert(consent);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
