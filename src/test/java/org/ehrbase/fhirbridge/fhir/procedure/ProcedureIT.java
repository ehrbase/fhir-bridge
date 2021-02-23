package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.jni.Proc;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ProcedureCompositionConverter;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Procedure;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Procedure Procedure} resource.
 */
class ProcedureIT extends AbstractMappingTestSetupIT {

    public ProcedureIT( ) {
        super("Procedure/", Procedure.class);
    }

    @Test
    void createProcedure() throws IOException {
       create("create-procedure.json");
    }

    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-procedure-with-default-profile.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Default profile is not supported for Procedure. One of the following profiles is expected: " +
                "[https://www.medizininformatik-initiative.de/fhir/core/modul-prozedur/StructureDefinition/Procedure]", exception.getMessage());
    }

    @Test
    void createWithNonExistingSubject() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-procedure-with-non-existing-subject.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : EhrId not found for subject '123456789'", exception.getMessage());
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Procedure procedure = (Procedure) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () -> {
             new ProcedureCompositionConverter().toComposition(((Procedure) procedure));
        });
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                // .registerValueObject(new ValueObjectDefinition(YourComposition.class, List.of("location")))
                .build();
    }
}
