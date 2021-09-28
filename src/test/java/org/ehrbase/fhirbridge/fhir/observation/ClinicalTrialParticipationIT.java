package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation.ClinicalTrialParticipationCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.GECCOStudienteilnahmeComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.GeccoStudienteilnahmeEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungRegistrierungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudienteilnahmeCluster;
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

public class ClinicalTrialParticipationIT extends AbstractMappingTestSetupIT {

    public ClinicalTrialParticipationIT() {
        super("Observation/ClinicalTrialParticipation/", Observation.class); //fhir-Resource
    }

    @Test
    void createClinicalTrialParticipation() throws IOException {
        create("create-clinical-trial-participation-yes-eudract.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void mappingYesEudraCT() throws  IOException {
        testMapping("create-clinical-trial-participation-yes-eudract.json",
                "paragon-clinical-trial-participation-yes-eudract.json");
    }

    @Test
    void mappingYesEudraCTNCT() throws  IOException {
        testMapping("create-clinical-trial-participation-yes-eudract-nct.json",
                "paragon-clinical-trial-participation-yes-eudract-nct.json");
    }

    @Test
    void mappingYesNCT() throws  IOException {
        testMapping("create-clinical-trial-participation-yes-nct.json",
                "paragon-clinical-trial-participation-yes-nct.json");
    }

    @Test
    void mappingNo() throws  IOException {
        testMapping("create-clinical-trial-participation-no.json",
                "paragon-clinical-trial-participation-no.json");
    }

    @Test
    void mappingUnknown() throws IOException {
        testMapping("create-clinical-trial-participation-unknown.json",
                "paragon-clinical-trial-participation-unknown.json");
    }

    @Test
    void mappingNotApplicable() throws IOException {
        testMapping("create-clinical-trial-participation-notapplicable.json",
                "paragon-clinical-trial-participation-notapplicable.json");
    }

    // #####################################################################################
    // check exceptions

    @Test
    void createInvalidCode() throws IOException {
        // copy of yes-eudract, manipulated line 70
        Exception exception = executeMappingException("create-clinical-trial-participation-yes-eudract-invalid-code.json");
        assertEquals("value code 99 is not supported", exception.getMessage());
    }

    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOStudienteilnahmeComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(GeccoStudienteilnahmeEvaluation.class)
                .registerValueObject(StudienteilnahmeCluster.class)
                .registerValueObject(StudiePruefungCluster.class)
                .registerValueObject(StudiePruefungRegistrierungCluster.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () ->
                new ClinicalTrialParticipationCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        ClinicalTrialParticipationCompositionConverter clinicalTrialParticipationCompositionConverter = new ClinicalTrialParticipationCompositionConverter();
        GECCOStudienteilnahmeComposition mapped = clinicalTrialParticipationCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}