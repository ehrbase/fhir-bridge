package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
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

    public ProcedureIT() {
        super("Procedure/", Procedure.class);
    }

    @Test
    void createProcedure() throws IOException {
        create("create-procedure.json");
    }

    @Test
    void createApheresisProcedure() throws IOException {
        create("create-apheresis-sct-datetime.json");
        create("create-apheresis-sct-period.json");
        create("create-apheresis-sct-period_2.json");
        create("create-apheresis-with-not-performed-performedDateTime.json");
        create("create-apheresis-with-not-unknown-performedDateTime.json");
    }

    @Test
    void createDialysisProcedure() throws IOException {
        create("create-dialysis-sct-datetime.json");
        create("create-dialysis-sct-period.json");
        create("create-dialysis-sct-period_2.json");
        create("create-dialysis-with-performedDateTime-not-performed.json");
        create("create-dialysis-with-performedDateTime-unknown.json");
        create("create-dialysis-with-performedPeriod-not-performed.json");
    }

    @Test
    void createExtracorporealMembraneOxygenation() throws IOException {
        create("create-extracorporeal-membrane-oxygenation-in-progress.json");
        create("create-extracorporeal-membrane-oxygenation-in-progress_sct_datetime.json");
        create("create-extracorporeal-membrane-oxygenation-in-progress_sct_period.json");
        create("create-extracorporeal-membrane-oxygenation-in-progress_sct_period_2.json");
        create("create-extracorporeal-membrane-oxygenation-no-done.json");
    }

    @Test
    void createPronePosition() throws IOException {
        create("create-prone-position-in-progress.json");
        create("create-prone-position-in-progress_sct-datetime.json");
        create("create-prone-position-in-progress_sct-period.json");
        create("create-prone-position-in-progress_sct-period2.json");
        create("create-prone-position-not-done.json");
    }

    @Test
    void createRadiologyProcedures() throws IOException {
        create("create-radiology-procedures-chest-x-ray.json");
        create("create-radiology-procedures-chest-x-ray_dcm_datetime.json");
        create("create-radiology-procedures-chest-x-ray_dcm_period.json");
        create("create-radiology-procedures-chest-x-ray_dcm_period_2.json");
        create("create-radiology-procedures-chest-x-ray_sct_datetime.json");
        create("create-radiology-procedures-chest-x-ray_sct_period.json");
        create("create-radiology-procedures-chest-x-ray_sct_period_2.json");
        create("create-radiology-procedures-computed-tomography.json");
        create("create-radiology-procedures-ultrasound.json");
    }

    @Test
    void createRespiratoryTherapies() throws IOException {
        create("create-respiratory-therapies-artificial-respiration.json");
        create("create-respiratory-therapies-artificial-respiration_sct-datetime.json");
        create("create-respiratory-therapies-artificial-respiration_sct-period.json");
        create("create-respiratory-therapies-artificial-respiration_sct-period_2.json");
        create("create-respiratory-therapies-invasive-ventilation-orotracheal.json");
        create("create-respiratory-therapies-invasive-ventilation-tracheotomy.json");
        create("create-respiratory-therapies-nasal-high-flow-oxygen-therapy.json");
        create("create-respiratory-therapies-non-invasive-ventilation.json");
        create("create-respiratory-therapies-with-not-performed-performedDateTime.json");
        create("create-respiratory-therapies-with-unknown-performedDateTime.json");
        create("create-respiratory-therapies-with-valid-performeddatetime.json");
        create("create-respiratory-therapies-with-valid-performedperiod.json");
    }

    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-procedure-with-default-profile.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Default profile is not supported for Procedure. " +
                "One of the following profiles is expected: " +
                "[https://www.medizininformatik-initiative.de/fhir/core/modul-prozedur/StructureDefinition/Procedure, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/apheresis, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/dialysis, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/extracorporeal-membrane-oxygenation, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/prone-position, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/radiology-procedures, " +
                "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/respiratory-therapies]", exception.getMessage());
    }

    @Test
    void createWithNonExistingSubject() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-procedure-with-non-existing-subject.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : EhrId not found for subject '123456789'", exception.getMessage());
    }

    @Override
    public Exception executeMappingUnprocessableEntityException(IBaseResource baseResource) {
        return assertThrows(UnprocessableEntityException.class, () -> {
            new ProcedureCompositionConverter().toComposition(((Procedure) baseResource));
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
