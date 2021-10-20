package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.diagnosticreportlab.DiagnosticReportLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.GECCODiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.DiagnosticReport DiagnosticReport} resource.
 */
class DiagnosticReportLabIT extends AbstractMappingTestSetupIT {

    public DiagnosticReportLabIT() {
        super("DiagnosticReport/DiagnosticReportLab/", DiagnosticReport.class);
    }

    @Test
    void createDiagnosticReport() throws IOException {
        create("create-diagnosticReport.json");
    }

    @Test
    void createDiagnosticReportMapping() throws IOException {
        testMapping("create-diagnosticReport.json", "paragon-create-diagnosticReport.json");
    }


    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-diagnosticReport-with-default-profile.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Default profile is not supported for DiagnosticReport. One of the following profiles is expected: " +
                "[https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/DiagnosticReportLab, https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/diagnostic-report-radiology]", exception.getMessage());
    }

    @Test
    void createWithInvalidCode() throws IOException {
        String resource = super.testFileLoader.loadResourceToString("create-diagnosticReport-with-invalid-code.json");

        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : This element does not match any known slice defined in the profile https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/DiagnosticReportLab", exception.getMessage());
    }


    @Override
    public Exception executeMappingException(String path) throws IOException {
        Condition condition = (Condition) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            (new GECCODiagnoseCompositionConverter()).convert(condition);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        DiagnosticReport resource = (DiagnosticReport) super.testFileLoader.loadResource(resourcePath);
        DiagnosticReportLabCompositionConverter compositionConverter = new DiagnosticReportLabCompositionConverter();
        GECCOLaborbefundComposition geccoLaborbefundComposition = compositionConverter.convert(resource);
        Diff diff = compareCompositions(getJavers(), paragonPath, geccoLaborbefundComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOLaborbefundComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(LaborergebnisObservation.class)
                .registerValueObject(LaborbefundKategorieElement.class)
                .registerValueObject(ProLaboranalytCluster.class)
                .registerValueObject(ProLaboranalytErgebnisStatusDvCodedText.class)
                .registerValueObject(ProLaboranalytMesswertDvQuantity.class)
                .build();
    }

}
