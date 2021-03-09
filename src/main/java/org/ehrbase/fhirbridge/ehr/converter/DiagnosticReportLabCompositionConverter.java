package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.observationlab.ObservationLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.convertercodes.CodeSystem.LOINC;

public class DiagnosticReportLabCompositionConverter implements CompositionConverter<GECCOLaborbefundComposition, DiagnosticReport> {

    private static final Logger LOG = LoggerFactory.getLogger(DiagnosticReportLabCompositionConverter.class);

    private final ObservationLabCompositionConverter observationConverter = new ObservationLabCompositionConverter();

    @Override
    public DiagnosticReport fromComposition(GECCOLaborbefundComposition composition) {
        if (composition == null) {
            return null;
        }
        return new DiagnosticReport();
    }

    @Override
    public GECCOLaborbefundComposition toComposition(DiagnosticReport diagnosticReport) {
        if (diagnosticReport == null) {
            return null;
        }

        LOG.debug("Contained size: {}", diagnosticReport.getContained().size());

        // one contained Observation is expected
        if (diagnosticReport.getContained().size() != 1) {
            throw new UnprocessableEntityException("One contained Observation was expected " + diagnosticReport.getContained().size() + " were received in DiagnosticReport " + diagnosticReport.getId());
        }
        if (diagnosticReport.getContained().get(0).getResourceType() != ResourceType.Observation) {
            throw new UnprocessableEntityException("One contained Observation was expected, contained is there but is not Observation, it is " + diagnosticReport.getContained().get(0).getResourceType().toString());
        }

        Observation observation = (Observation) diagnosticReport.getContained().get(0);

        GECCOLaborbefundComposition result = observationConverter.toComposition(observation);

        FeederAudit fa = CommonData.constructFeederAudit(diagnosticReport);
        result.setFeederAudit(fa);

        LaborergebnisObservation laborbefund = result.getLaborergebnis();

        ProLaboranalytCluster laboranalytCluster = laborbefund.getProLaboranalyt();

        laborbefund.setTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborbefund.setLabortestKategorieDefiningCode(getLabortestCode(diagnosticReport.getCategory().get(0)));
        laborbefund.setSchlussfolgerungValue(diagnosticReport.getConclusion());
        laborbefund.setProLaboranalyt(laboranalytCluster);
        return result;
    }


    private LabortestKategorieDefiningCode getLabortestCode(CodeableConcept codeableConcept) {
        List<Coding> codings = codeableConcept.getCoding();
        for(Coding coding: codings){
            if(coding.getSystem().equals(LOINC.getUrl())){
               return mapLabortest(coding.getCode());
            }
        }
        throw new UnprocessableEntityException("The Category loinc code is missing");
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
        }else if (code.equals(LabortestKategorieDefiningCode.SEROLOGY_AND_BLOOD_BANK_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.SEROLOGY_AND_BLOOD_BANK_STUDIES_SET;
        }else if (code.equals(LabortestKategorieDefiningCode.URINALYSIS_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.URINALYSIS_STUDIES_SET;
        }else if (code.equals(LabortestKategorieDefiningCode.TOXICOLOGY_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.TOXICOLOGY_STUDIES_SET;
        }else if (code.equals(LabortestKategorieDefiningCode.LABORATORY_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.LABORATORY_STUDIES_SET;
        }else if (code.equals(LabortestKategorieDefiningCode.COAGULATION_STUDIES_SET.getCode())) {
            return LabortestKategorieDefiningCode.COAGULATION_STUDIES_SET;
        }else{
            throw new UnprocessableEntityException("The code" +code+"is not supported for category.coding");
        }
    }



}