package org.ehrbase.fhirbridge.fhir.diagnosticreport;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdslaborbefund.KDSLaborbefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.KDSLaborberichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.LaborbefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytMesswertDvQuantity;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytMesswertElement;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialCluster;
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

    @Test
    void createDiagnosticReportSpecimenMapping() throws IOException {
        testMapping("create-diagnosticReport-specimen.json", "paragon-create-diagnosticReport-specimen.json");
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
        KDSLaborbefundCompositionConverter compositionConverter = new KDSLaborbefundCompositionConverter();
        KDSLaborberichtComposition laborbefundComposition = compositionConverter.convert(resource);
        Diff diff = compareCompositions(getJavers(), paragonPath, laborbefundComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(KDSLaborberichtComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(LaborergebnisObservation.class)
                .registerValueObject(FallidentifikationCluster.class)
                .registerValueObject(ProLaboranalytMesswertDvQuantity.class)
                .registerValueObject(LaborbefundObservation.class)
                .registerValueObject(ProLaboranalytCluster.class)
                .registerValueObject(ProLaboranalytMesswertElement.class)
                .registerValueObject(ProbenmaterialCluster.class)
                .build();
    }

}
