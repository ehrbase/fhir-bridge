package org.ehrbase.fhirbridge.fhir.consent;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
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
        super("Consent/DNR/", Consent.class); //fhir-Resource
    }

    @Test
    void createConsentDNR() throws IOException {
        create("create-consent-dnr.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void mappingConsentDNRStatusDraft() throws IOException {
        testMapping("create-consent-dnr-status-draft.json",
                "paragon-consent-dnr-status-draft.json");
    }

    @Test
    void mappingConsentDNRStatusProposed() throws IOException {
        testMapping("create-consent-dnr-status-proposed.json",
                "paragon-consent-dnr-status-proposed.json");
    }

    @Test
    void mappingConsentDNRStatusActive() throws IOException {
        testMapping("create-consent-dnr-status-active.json",
                "paragon-consent-dnr-status-active.json");
    }

    @Test
    void mappingConsentDNRStatusRejected() throws IOException {
        testMapping("create-consent-dnr-status-rejected.json",
                "paragon-consent-dnr-status-rejected.json");
    }

    @Test
    void mappingConsentDNRStatusInactive() throws IOException {
        testMapping("create-consent-dnr-status-inactive.json",
                "paragon-consent-dnr-status-inactive.json");
    }

    @Test
    void mappingConsentDNRStatusEnteredInError() throws IOException {
        testMapping("create-consent-dnr-status-enteredinerror.json",
                "paragon-consent-dnr-status-enteredinerror.json");
    }

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
        return assertThrows(Exception.class, () ->
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
