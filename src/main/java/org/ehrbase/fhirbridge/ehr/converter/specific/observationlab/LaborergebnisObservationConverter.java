package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.hl7.fhir.r4.model.Observation;
import java.util.HashMap;
import java.util.Map;

public class LaborergebnisObservationConverter extends ObservationToObservationConverter<LaborergebnisObservation> {

    private static final Map<String, LabortestKategorieDefiningCode> labortestBezeichnungLOINCDefiningcodeMap
            = new HashMap<>();


    @Override
    protected LaborergebnisObservation convertInternal(Observation resource) {
        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        initialiseLabortestBezeichnungMap();
        ProLaboranalytCluster laboranalyt = new LaborAnalytConverter().convert(resource);
        setLaborergebnisKategorieDefiningCode(laborergebnisObservation, resource);
        setProbe(resource, laborergebnisObservation);

        laborergebnisObservation.setProLaboranalyt(laboranalyt);
        return laborergebnisObservation;
    }

    private void initialiseLabortestBezeichnungMap() {
        for (LabortestKategorieDefiningCode code : LabortestKategorieDefiningCode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                labortestBezeichnungLOINCDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    private void setProbe(Observation resource, LaborergebnisObservation laborergebnisObservation) {
        if (!resource.getSpecimen().isEmpty()) {
            laborergebnisObservation.getProbe().add(new SpecimenConverter().convert(resource.getSpecimenTarget()));
        }
    }

    private void setLaborergebnisKategorieDefiningCode(LaborergebnisObservation laborergebnisObservation, Observation resource) {
        if (resource.getCategory().get(0).getCoding().get(0).getSystem().equals(CodeSystem.LOINC.getUrl())) {
            String loincCode = resource.getCategory().get(0).getCoding().get(0).getCode();
            LabortestKategorieDefiningCode categoryDefiningcode = labortestBezeichnungLOINCDefiningcodeMap.get(loincCode);
            if (categoryDefiningcode == null) {
                throw new ConversionException("Unknown LOINC code in observation");
            }
            laborergebnisObservation.setLabortestKategorieDefiningCode(categoryDefiningcode);
        } else {
            throw new ConversionException("No LOINC code in observation");
        }
    }






}
