package org.ehrbase.fhirbridge.fhir.consent;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.dnranordnung.DnrAnordnungCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.DNRAnordnungComposition;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.DnrAnordnungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.DnrAnordnungKategorieElement;
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

    @Test
    void mappingIntegrationTest() throws IOException {
        create("consent-example.json");
    }

    // #####################################################################################
    // check payload
    @Test
    void mappingNormal() throws IOException {
        testMapping("consent-example.json",
                "paragon-consent-dnr-normal.json");
    }

    @Test
    void mappingNormal_2() throws IOException {
        testMapping("consent-example-2.json",
                "paragon-consent-dnr-normal-2.json");
    }

    @Test
    void mappingNormal_3() throws IOException {
        testMapping("consent-example-3.json",
                "paragon-consent-dnr-normal-3.json");
    }


    // #####################################################################################
    // check exceptions

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(DNRAnordnungComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(DnrAnordnungEvaluation.class)
                .registerValueObject(DnrAnordnungKategorieElement.class)
                .build();
    }

    public Javers getJaversIgnoreStartTime() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(DNRAnordnungComposition.class, List.of("location", "feederAudit", "startTimeValue")))
                .registerValueObject(DnrAnordnungEvaluation.class)
                .registerValueObject(DnrAnordnungKategorieElement.class)
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
        Diff diff = compareCompositions(getJaversIgnoreStartTime(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
