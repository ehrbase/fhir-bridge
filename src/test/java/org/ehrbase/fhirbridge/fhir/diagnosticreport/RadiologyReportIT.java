package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import ca.uhn.fhir.parser.DataFormatException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerBefund.RadiologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.DiagnosticReport;
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

public class RadiologyReportIT extends AbstractMappingTestSetupIT {

    public RadiologyReportIT() {
        super("DiagnosticReport/RadiologyReport/", DiagnosticReport.class);
    }

    @Test
    void createNormalFinding() throws IOException {
        create("create-radiology-report-normal-finding.json");
    }

    @Test
    void mappingNormalFinding() throws IOException {
        testMapping("create-radiology-report-normal-finding.json", "paragon-radiology-report-normal-finding.json");
    }

    @Test
    void mappingTypicalFinding() throws IOException {
        testMapping("create-radiology-report-typical-finding.json", "paragon-radiology-report-typical-finding.json");
    }

    @Test
    void mappingUnspecificFinding() throws IOException {
        testMapping("create-radiology-report-unspecific-finding.json", "paragon-radiology-report-unspecific-finding.json");
    }

    @Test
    void createInvalidBefund() throws IOException {
        Exception exception = executeMappingException("create-radiology-report-invalid-befund.json");
        assertEquals("The SNOMED code: 118247008:363713009=asd, is not supported for radiology report !", exception.getMessage());
    }

    //BSa wieso hier die unterschiedliche Art die Exception entgegen zu nehmen?
    @Test
    void createInvalidNameDerUntersuchung() throws IOException {
        Exception exception = executeMappingException("create-radiology-report-invalid-kategorie.json");
        assertEquals("The LOINC code: safs-0 is not valid for radiology report!", exception.getMessage());
    }

    @Test
    void createInvalidStatus() throws IOException {
        try {
            super.testFileLoader.loadResource("create-radiology-report-invalid-status.json");
        } catch (DataFormatException dataFormatException) {
            assertEquals("[element=\"status\"] Invalid attribute value \"asd\": Unknown DiagnosticReportStatus code 'asd'", dataFormatException.getMessage());
        }
    }

    @Test
    void createInvalidKategorie() throws IOException {
        Exception exception = executeMappingException("create-radiology-report-invalid-untersuchung.json");
        assertEquals("The Loinc code sfds-4 is not supported for radiology report !", exception.getMessage());
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        DiagnosticReport radiologyReport = (DiagnosticReport) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () -> {
            new RadiologischerBefundCompositionConverter().convert(radiologyReport);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        DiagnosticReport diagnosticReport = (DiagnosticReport) super.testFileLoader.loadResource(resourcePath);
        RadiologischerBefundCompositionConverter radiologischerBefundConverter = new RadiologischerBefundCompositionConverter();
        GECCORadiologischerBefundComposition mappedGeccoRadiologischerBefundComposition = radiologischerBefundConverter.convert(diagnosticReport);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedGeccoRadiologischerBefundComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCORadiologischerBefundComposition.class, List.of("location", "feederAudit")))
                .registerValueObject((BildgebendesUntersuchungsergebnisObservation.class))
                .registerValueObject((RadiologischerBefundKategorieElement.class))
                .build();
    }
}
