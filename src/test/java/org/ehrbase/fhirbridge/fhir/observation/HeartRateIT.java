package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation.ClinicalTrialParticipationCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.heartrate.HerzfrequenzCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.GECCOStudienteilnahmeComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
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

public class HeartRateIT extends AbstractMappingTestSetupIT {


    public HeartRateIT() {
        super("Observation/HeartRate/", Observation.class);
    }

    @Test
    void mappingYesEudraCT() throws  IOException {
        testMapping("create-clinical-trial-participation-yes-eudract.json",
                "paragon-clinical-trial-participation-yes-eudract.json");
    }

    // #####################################################################################
    // check exceptions

    @Test
    void createInvalidSystem() throws IOException {
        // copy of yes-eudract, manipulated line 71
        Exception exception = executeMappingException("create-clinical-trial-participation-yes-eudract-invalid-system.json");
        assertEquals("The system is not correct. It should be 'http://snomed.info/sct', but it was 'http://loinc.org'.", exception.getMessage());
    }

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
                .registerValueObject(new ValueObjectDefinition(HerzfrequenzComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(HerzfrequenzObservation.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () ->
                new HerzfrequenzCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        HerzfrequenzCompositionConverter herzfrequenzCompositionConverter = new HerzfrequenzCompositionConverter();
        HerzfrequenzComposition mapped = herzfrequenzCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }

}
