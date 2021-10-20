package org.ehrbase.fhirbridge.ehr.converter.specific.diagnosticreportlab;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.InvalidStatusCodeException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.observationlab.LaborergebnisObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborbefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticReportLabCompositionConverter extends DiagnosticReportToCompositionConverter<GECCOLaborbefundComposition> {

    @Override
    protected GECCOLaborbefundComposition convertInternal(DiagnosticReport diagnosticReport) {
        if (diagnosticReport.hasContained()) {
            if (diagnosticReport.getContained().get(0).getResourceType() != ResourceType.Observation) {
                throw new ConversionException("One contained Observation was expected, the contained is present but no observation is within. Instance: " + diagnosticReport.getContained().get(0).getResourceType().toString());
            }
            GECCOLaborbefundComposition result = new GECCOLaborbefundComposition();
            result.setStatusDefiningCode(convertStatus(diagnosticReport));
            result.setKategorie(convertCategory(diagnosticReport));
            result.setLaborergebnis(convertLaborergebnis(diagnosticReport));
            return result;
        } else {
            throw new ConversionException("One contained Observation was expected " + diagnosticReport.getContained().size() + " were received in DiagnosticReport " + diagnosticReport.getId());
        }
    }

    private List<LaborergebnisObservation> convertLaborergebnis(DiagnosticReport diagnosticReport) {
        List<LaborergebnisObservation> laborergebnisObservationList = new ArrayList<>();
        for (Resource resource : diagnosticReport.getContained()) {
            if (resource.getResourceType() == ResourceType.Observation) {
                LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservationConverter().convert((Observation) resource);
                injectSchlussfolgerung(laborergebnisObservation, diagnosticReport);
                laborergebnisObservationList.add(laborergebnisObservation);
            }
        }
        return laborergebnisObservationList;
    }

    private List<LaborbefundKategorieElement> convertCategory(DiagnosticReport diagnosticReport) { //TODO alter template
        List<LaborbefundKategorieElement> laborbefundKategorieElementList = new ArrayList<>();
        for (CodeableConcept codeableConcept : diagnosticReport.getCategory()) {
            for (Coding coding : codeableConcept.getCoding()) {
                LaborbefundKategorieElement laborbefundKategorieElement = new LaborbefundKategorieElement();
                laborbefundKategorieElement.setValue(coding.getCode());
                laborbefundKategorieElementList.add(laborbefundKategorieElement);
            }
        }
        return laborbefundKategorieElementList;
    }

    private StatusDefiningCode convertStatus(DiagnosticReport diagnosticReport) {
        DiagnosticReport.DiagnosticReportStatus status = diagnosticReport.getStatus();
        if (status.equals(DiagnosticReport.DiagnosticReportStatus.FINAL)) {
            return StatusDefiningCode.FINAL;
        } else if (status.equals(DiagnosticReport.DiagnosticReportStatus.CORRECTED)) {
            return StatusDefiningCode.GEAENDERT;
        } else if (status.equals(DiagnosticReport.DiagnosticReportStatus.REGISTERED)) {
            return StatusDefiningCode.REGISTRIERT;
        } else if (status.equals(DiagnosticReport.DiagnosticReportStatus.PRELIMINARY)) {
            return StatusDefiningCode.VORLAEUFIG;
        } else {
            throw new InvalidStatusCodeException(status.toString());
        }
    }


    private void injectSchlussfolgerung(LaborergebnisObservation laborergebnisObservation, DiagnosticReport diagnosticReport) {
        if (diagnosticReport.hasConclusion()) {
            laborergebnisObservation.setSchlussfolgerungValue(diagnosticReport.getConclusion());
        }
    }

}
