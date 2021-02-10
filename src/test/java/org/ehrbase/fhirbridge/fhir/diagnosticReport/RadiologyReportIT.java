package org.ehrbase.fhirbridge.fhir.diagnosticReport;

import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund.RadiologischerBefundConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RadiologyReportIT extends AbstractMappingTestSetupIT {

    public RadiologyReportIT() {
        super("DiagnosticReport/RadiologyReport/", DiagnosticReport.class);
    }

    @Test
    void createNormalFinding() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("create-radiology-report-normal-finding.json").getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();
        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }

    @Test
    void mappingNormalFinding() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("create-radiology-report-normal-finding.json").getInputStream(), StandardCharsets.UTF_8);
        IParser parser = context.newJsonParser();
        DiagnosticReport diagnosticReport = parser.parseResource(DiagnosticReport.class, resource);
        RadiologischerBefundConverter radiologischerBefundConverter = new RadiologischerBefundConverter();
        GECCORadiologischerBefundComposition mappedGeccoRadiologischerBefundComposition = radiologischerBefundConverter.toComposition(diagnosticReport);
        Diff diff = compareCompositions(getJavers(), "paragon-radiology-report-normal-finding.json", mappedGeccoRadiologischerBefundComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Test
    void mappingTypicalFinding() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("create-radiology-report-typical-finding.json").getInputStream(), StandardCharsets.UTF_8);
        IParser parser = context.newJsonParser();
        DiagnosticReport diagnosticReport = parser.parseResource(DiagnosticReport.class, resource);
        RadiologischerBefundConverter radiologischerBefundConverter = new RadiologischerBefundConverter();
        GECCORadiologischerBefundComposition mappedGeccoRadiologischerBefundComposition = radiologischerBefundConverter.toComposition(diagnosticReport);
        Diff diff = compareCompositions(getJavers(), "paragon-radiology-report-typical-finding.json", mappedGeccoRadiologischerBefundComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Test
    void mappingUnspecificFinding() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("create-radiology-report-unspecific-finding.json").getInputStream(), StandardCharsets.UTF_8);
        IParser parser = context.newJsonParser();
        DiagnosticReport diagnosticReport = parser.parseResource(DiagnosticReport.class, resource);
        RadiologischerBefundConverter radiologischerBefundConverter = new RadiologischerBefundConverter();
        GECCORadiologischerBefundComposition mappedGeccoRadiologischerBefundComposition = radiologischerBefundConverter.toComposition(diagnosticReport);
        Diff diff = compareCompositions(getJavers(), "paragon-radiology-report-unspecific-finding.json", mappedGeccoRadiologischerBefundComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Test
    void createInvalidBefund() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-radiology-report-invalid-befund.json"));
        assertEquals("The SNOMED code: asdasd, is not supported for radiology report !", exception.getMessage());
    }

    @Test
    void createInvalidNameDerUntersuchung() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-radiology-report-invalid-kategorie.json"));
        assertEquals("The LOINC code: safs-0 is not valid for radiology report!", exception.getMessage());
    }

    @Test
    void createInvalidStatus() throws IOException {
        try{
            super.testFileLoader.loadResource("create-radiology-report-invalid-status.json");
        }catch (DataFormatException dataFormatException){
            assertEquals("[element=\"status\"] Invalid attribute value \"asd\": Unknown DiagnosticReportStatus code 'asd'", dataFormatException.getMessage());
        }
    }

    @Test
    void createInvalidKategorie() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("create-radiology-report-invalid-untersuchung.json"));
        assertEquals("The Loinc code sfds-4 is not supported for radiology report !", exception.getMessage());
    }

    @Override
    public Exception executeMappingUnprocessableEntityException(IBaseResource radiologyReport) {
        return assertThrows(UnprocessableEntityException.class, () -> {
            new RadiologischerBefundConverter().toComposition((DiagnosticReport) radiologyReport);
        });
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCORadiologischerBefundComposition.class, List.of("location")))
                .registerValueObject((BildgebendesUntersuchungsergebnisObservation.class))
                .registerValueObject((RadiologischerBefundKategorieElement.class))
                .build();
    }
}
