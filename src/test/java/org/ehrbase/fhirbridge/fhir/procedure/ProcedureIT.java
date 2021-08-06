package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.procedure.ProcedureCompositionConverter;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Procedure;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Procedure Procedure} resource.
 */
class ProcedureIT extends AbstractMappingTestSetupIT {

    public ProcedureIT() {
        super("Procedure/Procedure/", Procedure.class);
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

        assertEquals("HTTP 422 : Default profile is not supported for Procedure. " +
                "One of the following profiles is expected: " +
                "[https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/apheresis, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/dialysis, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/extracorporeal-membrane-oxygenation, " +
                "https://www.medizininformatik-initiative.de/fhir/core/modul-prozedur/StructureDefinition/Procedure, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/prone-position, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/radiology-procedures, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/respiratory-therapies]", exception.getMessage());
    }

    @Test
    void createWithNonExistingSubject() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-procedure-with-non-existing-subject.json");
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Procedure procedure = (Procedure) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new ProcedureCompositionConverter().convert(procedure);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        // your mapping compared to paragon file
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                // .registerValueObject(new ValueObjectDefinition(YourComposition.class, List.of("location")))
                .build();
    }
}
