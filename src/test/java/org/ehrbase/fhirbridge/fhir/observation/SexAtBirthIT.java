package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.sexatbirth.SexAtBirthConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Period;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SexAtBirthIT extends AbstractMappingTestSetupIT {

    public SexAtBirthIT() {
        super("Observation/SexAtBirth/", Observation.class);
    }

    @Test
    void createSexAtBirth() throws IOException {
        create("create-sex-at-birth.json");
    }

    @Test
    void createSexAtBirthMapping() throws IOException {
        testMapping("create-sex-at-birth.json", "paragon-create-sex-at-birth.json");
    }

    @Test
    void createSexAtBirthMappingFemale() throws IOException {
        testMapping("create-sex-at-birth-female.json", "paragon-create-sex-at-birth-female.json");
    }

    @Test
    void createSexAtBirthMappingMale() throws IOException {
        testMapping("create-sex-at-birth-male.json", "paragon-create-sex-at-birth-male.json");
    }

    @Test
    void createSexAtBirthMappingUnknown() throws IOException {
        testMapping("create-sex-at-birth-unknown.json", "paragon-create-sex-at-birth-unknown.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOPersonendatenComposition.class, List.of("location",  "feederAudit")))
                .registerValueObject(GeschlechtEvaluation.class)
                .registerValueObject(PersonendatenAdminEntry.class)
                .registerValueObject(AlterObservation.class)
                .registerValueObject((EthnischerHintergrundCluster.class))
                .registerValueObject((DatenZurGeburtCluster.class))
                .registerValueObject((GeschlechtEvaluation.class))
                .registerValueObject((AngabenZumTodCluster.class))
                .registerValueObject((GeccoPersonendatenKategorieElement.class))
                .registerValueObject((Period.class))
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new SexAtBirthConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        SexAtBirthConverter sexAtBirthConverter = new SexAtBirthConverter();
        GECCOPersonendatenComposition mapped = sexAtBirthConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
