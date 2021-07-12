package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaborergebnisObservationConverter extends ObservationToObservationConverter<LaborergebnisObservation> {

    @Override
    protected LaborergebnisObservation convertInternal(Observation resource) {
        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        initialiseLabortestBezeichnungMap();
        ProLaboranalytCluster laboranalyt = new LaborAnalytConverter().convert(resource);
        setKategorieValue(laborergebnisObservation, resource);
        setProbe(resource, laborergebnisObservation);
        laborergebnisObservation.setProLaboranalyt(laboranalyt);
        return laborergebnisObservation;
    }

    private void initialiseLabortestBezeichnungMap() {
        for (LabortestKategorieDefiningCode code : LabortestKategorieDefiningCode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                LabortestKategorieDefiningCode.getCodesAsMap().put(code.getCode(), code);
            }
        }
    }

    private void setProbe(Observation resource, LaborergebnisObservation laborergebnisObservation) {
        if (resource.hasSpecimen()) {
            laborergebnisObservation.getProbe().add(new SpecimenConverter().convert(resource.getSpecimenTarget()));
        }
    }

    private void setKategorieValue(LaborergebnisObservation observation, Observation resource) {
        for(CodeableConcept codeableConcept : resource.getCategory()){
            convertKategorieValue(codeableConcept, observation);
        }
    }

    private void convertKategorieValue(CodeableConcept codeableConcept, LaborergebnisObservation observation) {
        for(Coding coding : codeableConcept.getCoding()){
            if (coding.getSystem().equals(CodeSystem.LOINC.getUrl()) && LabortestKategorieDefiningCode.getCodesAsMap().containsKey(coding.getCode())) {
                observation.setLabortestKategorieDefiningCode(LabortestKategorieDefiningCode.getCodesAsMap().get(coding.getCode()));
            } else {
                throw new ConversionException("No LOINC Code or unsupported LOINC Code in Observation");
            }
        }
    }
}
