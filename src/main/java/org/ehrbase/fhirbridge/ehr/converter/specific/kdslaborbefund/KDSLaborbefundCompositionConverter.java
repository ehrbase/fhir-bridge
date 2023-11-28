package org.ehrbase.fhirbridge.ehr.converter.specific.kdslaborbefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.InvalidStatusCodeException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdsobservationlab.LaborergebnisObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.KDSLaborberichtComposition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class KDSLaborbefundCompositionConverter extends DiagnosticReportToCompositionConverter<KDSLaborberichtComposition> {
    @Override
    protected KDSLaborberichtComposition convertInternal(DiagnosticReport diagnosticReport) {
        List<Observation> observationList = new ArrayList<>();
        if (diagnosticReport.hasContained()) {
            for (Resource resource : diagnosticReport.getContained()) {
                if (resource.getResourceType() == ResourceType.Observation) {
                    observationList.add((Observation) resource);
                }
            }
        } else {
            throw new ConversionException("One contained Observation was expected " + diagnosticReport.getContained().size() + " were received in DiagnosticReport " + diagnosticReport.getId());
        }

        if (observationList.size() == 0) {
            throw new ConversionException("One contained Observation was expected, the contained is present but no observation is within. Instance: " + diagnosticReport.getContained().get(0).getResourceType().toString());
        } else {
            KDSLaborberichtComposition result = new KDSLaborberichtComposition();
            result.setStatusValue(convertStatus(diagnosticReport.getStatus()));
            LaborergebnisObservationConverter laborergebnisObservationConverter = new LaborergebnisObservationConverter(observationList);
            result.setLaborbefund(laborergebnisObservationConverter.convert((Observation) diagnosticReport.getContained().get(0)));
            return result;
        }
    }

    private String convertStatus(DiagnosticReport.DiagnosticReportStatus diagnosticReport) {
        if (diagnosticReport.equals(DiagnosticReport.DiagnosticReportStatus.FINAL)) {
            return "final";
        } else if (diagnosticReport.equals(DiagnosticReport.DiagnosticReportStatus.CORRECTED)) {
            return "geänedert";
        } else if (diagnosticReport.equals(DiagnosticReport.DiagnosticReportStatus.REGISTERED)) {
            return "registriert";
        } else if (diagnosticReport.equals(DiagnosticReport.DiagnosticReportStatus.PRELIMINARY)) {
            return "vorläufig";
        } else {
            throw new InvalidStatusCodeException(diagnosticReport.toString());
        }
    }

}
