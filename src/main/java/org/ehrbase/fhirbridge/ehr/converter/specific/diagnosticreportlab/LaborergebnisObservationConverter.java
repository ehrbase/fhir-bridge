package org.ehrbase.fhirbridge.ehr.converter.specific.diagnosticreportlab;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.observationlab.ObservationLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.LOINC;

public class LaborergebnisObservationConverter extends DiagnosticReportToObservationConverter<LaborergebnisObservation> {

    @Override
    protected LaborergebnisObservation convertInternal(DiagnosticReport diagnosticReport) {
        if(diagnosticReport.hasContained()){
            Observation observation = (Observation) diagnosticReport.getContained().get(0);
            LaborergebnisObservation laborbefund = new ObservationLabCompositionConverter().convert(observation).getLaborergebnis();
            ProLaboranalytCluster laboranalytCluster = laborbefund.getProLaboranalyt();
            laborbefund.setLabortestKategorieDefiningCode(getLabortestCode(observation.getCategory().get(0)));
            mapSchlussfolgerung(laborbefund, diagnosticReport);
            laborbefund.setProLaboranalyt(laboranalytCluster);
            return laborbefund;
        }else{
            throw new ConversionException("The diagnositc report has to contain an observation with the lab values.");
        }
    }

    private void mapSchlussfolgerung(LaborergebnisObservation laborbefund, DiagnosticReport diagnosticReport) {
        if(diagnosticReport.hasConclusion()){
            laborbefund.setSchlussfolgerungValue(diagnosticReport.getConclusion());
        }
    }

    private LabortestKategorieDefiningCode getLabortestCode(CodeableConcept codeableConcept) {
        List<Coding> codings = codeableConcept.getCoding();
        for (Coding coding : codings) {
            if (coding.getSystem().equals(LOINC.getUrl())) {
                return mapLabortest(coding.getCode());
            }
        }
        throw new ConversionException("The Category loinc code is missing");
    }

    private LabortestKategorieDefiningCode mapLabortest(String code) {
        if (code.equals(LabortestKategorieDefiningCode.MICROBIOLOGY_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.MICROBIOLOGY_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.BLOOD_GAS_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.BLOOD_GAS_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.CALCULATED_AND_DERIVED_VALUES_SET.getCode())) {
            return LabortestKategorieDefiningCode.CALCULATED_AND_DERIVED_VALUES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.CARDIAC_BIOMARKERS_SET.getCode())) {
            return LabortestKategorieDefiningCode.CARDIAC_BIOMARKERS_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.CHEMISTRY_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.CHEMISTRY_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.HEMATOLOGY_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.HEMATOLOGY_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.SEROLOGY_AND_BLOOD_BANK_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.SEROLOGY_AND_BLOOD_BANK_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.URINALYSIS_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.URINALYSIS_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.TOXICOLOGY_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.TOXICOLOGY_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.LABORATORY_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.LABORATORY_STUDIES_SET;
        } else if (code.equals(LabortestKategorieDefiningCode.COAGULATION_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.COAGULATION_STUDIES_SET;
        } else {
            throw new ConversionException("The code" + code + "is not supported for category.coding");
        }
    }

}
