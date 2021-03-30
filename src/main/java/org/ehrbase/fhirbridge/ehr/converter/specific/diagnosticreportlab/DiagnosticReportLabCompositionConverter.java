package org.ehrbase.fhirbridge.ehr.converter.specific.diagnosticreportlab;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.observationlab.ObservationLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.LOINC;

public class DiagnosticReportLabCompositionConverter extends CompositionConverter<DiagnosticReport, GECCOLaborbefundComposition> {

    private static final Logger LOG = LoggerFactory.getLogger(DiagnosticReportLabCompositionConverter.class);

    private final ObservationLabCompositionConverter observationConverter = new ObservationLabCompositionConverter();

    @Override
    protected GECCOLaborbefundComposition convertInternal(DiagnosticReport resource) {
        LOG.debug("Contained size: {}", resource.getContained().size());

        // one contained Observation is expected
        if (resource.getContained().size() != 1) {
            throw new ConversionException("One contained Observation was expected " + resource.getContained().size() + " were received in DiagnosticReport " + resource.getId());
        }
        if (resource.getContained().get(0).getResourceType() != ResourceType.Observation) {
            throw new ConversionException("One contained Observation was expected, contained is there but is not Observation, it is " + resource.getContained().get(0).getResourceType().toString());
        }

        Observation observation = (Observation) resource.getContained().get(0);

        GECCOLaborbefundComposition result = observationConverter.convert(observation);

        LaborergebnisObservation laborbefund = result.getLaborergebnis();

        ProLaboranalytCluster laboranalytCluster = laborbefund.getProLaboranalyt();

        laborbefund.setTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborbefund.setLabortestKategorieDefiningCode(getLabortestCode(resource.getCategory().get(0)));
        laborbefund.setSchlussfolgerungValue(resource.getConclusion());
        laborbefund.setProLaboranalyt(laboranalytCluster);
        return result;
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
