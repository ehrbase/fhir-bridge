package org.ehrbase.fhirbridge.fhir.bundle;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdslaborbefund.KDSLaborbefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.KDSLaborberichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractBundleMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.converter.BloodGasPanelConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.DiagnosticReportLabConverter;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
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
 * Integration tests for {@link DiagnosticReport DiagnosticReport} resource.
 */
class DiagnosticReportLabIT extends AbstractBundleMappingTestSetupIT {

    public DiagnosticReportLabIT() {
        super("Bundle/DiagnosticReport/", Bundle.class);
    }

    @Test
    void createDiagnosticReport() throws IOException {
        create("create-diagnosticReport-specimen.json");
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
        Bundle resource = (Bundle) super.testFileLoader.loadResource(resourcePath);
        DiagnosticReportLabConverter diagnosticReportLabConverter = new DiagnosticReportLabConverter();
        DiagnosticReport diagnosticReport = diagnosticReportLabConverter.convert(resource);
        KDSLaborbefundCompositionConverter compositionConverter = new KDSLaborbefundCompositionConverter();
        KDSLaborberichtComposition laborbefundComposition = compositionConverter.convert(diagnosticReport);
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
                .registerValueObject(new ValueObjectDefinition(ProLaboranalytProbeIdDvUri.class, List.of("probeIdValue")))
                .registerValueObject(LaborbefundObservation.class)
                .registerValueObject(ProLaboranalytCluster.class)
                .registerValueObject(ProLaboranalytMesswertElement.class)
                .registerValueObject(ProbenmaterialCluster.class)
                .build();
    }

}
