package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeCluster;
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
        setLaborergebnisKategorieDefiningCode(laborergebnisObservation, resource);
        setProbe(resource, laborergebnisObservation);
        laborergebnisObservation.setProLaboranalyt(laboranalyt);
        return laborergebnisObservation;
    }

/*    private void mapMethod(Observation resource, LaborergebnisObservation laborergebnisObservation) {
        if (resource.hasMethod() && resource.getMethod().hasCoding()) {
            if(!laborergebnisObservation.getProbe().isEmpty()){
                laborergebnisObservation.getProbe().get(0).setProbenentnahmemethodeValue(resource.getMethod().getCoding().get(0).getDisplay());
            }else{
                ProbeCluster probe = new ProbeCluster();
                probe.setProbenentnahmemethodeValue(resource.getMethod().getCoding().get(0).getDisplay());
                laborergebnisObservation.setProbe(List.of(probe));
            }
        }
    }*/

    private void initialiseLabortestBezeichnungMap() {
        for (LabortestKategorieDefiningCode code : LabortestKategorieDefiningCode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                LabortestKategorieDefiningCode.getCodesAsMap().put(code.getCode(), code);
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
            LabortestKategorieDefiningCode categoryDefiningcode = LabortestKategorieDefiningCode.getCodesAsMap().get(loincCode);
            if (categoryDefiningcode == null) {
                throw new ConversionException("Unknown LOINC code in observation");
            }
            laborergebnisObservation.setLabortestKategorieDefiningCode(categoryDefiningcode);
        } else {
            throw new ConversionException("No LOINC code in observation");
        }
    }






}
