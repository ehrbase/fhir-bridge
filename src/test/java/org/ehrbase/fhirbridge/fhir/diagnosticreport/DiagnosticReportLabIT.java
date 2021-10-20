package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.diagnosticreportlab.DiagnosticReportLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Condition;
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
                .registerValueObject(new ValueObjectDefinition(ProLaboranalytCluster.class, List.of("zeitpunktErgebnisStatusValue")))// is a instant which has no zone when the CI is testing that it fails since the CI uses another zone, mappes is the system default zone
                .registerValueObject(ProLaboranalytErgebnisStatusDvCodedText.class)
                .registerValueObject(ProLaboranalytMesswertDvQuantity.class)
                .build();
    }

}
