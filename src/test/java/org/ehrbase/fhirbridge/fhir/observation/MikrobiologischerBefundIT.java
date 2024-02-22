package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdspatient.KDSPatientCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur.MibiBefundConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.KDSPersonComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.*;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.MikrobiologischerBefundComposition;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.observation.validator.MibiKulturValidator;
import org.ehrbase.fhirbridge.fhir.patient.validator.KdsPersonValidator;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;

import java.io.IOException;
import java.time.Period;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MikrobiologischerBefundIT  extends AbstractMappingTestSetupIT {

    public MikrobiologischerBefundIT() {
        super("Observation/MikrobiologischerBefund/", Observation.class); //fhir-Resource
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(MikrobiologischerBefundComposition.class, List.of("location", "feederAudit")))
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new MibiBefundConverter().convert((observation))
        );
    }


    public Exception executeValidatorException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new MibiKulturValidator().validateRequest(observation, null);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {

    }
}
