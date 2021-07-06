package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas;


import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter.KohlendioxidpartialdruckConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter.PhWertConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter.SauerstoffpartialdruckConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter.SauerstoffsaettigungConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.LabortestBezeichnungDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

class LaborergebnisBefundObservationConverter extends ObservationToObservationConverter<LaborergebnisObservation> {

    @Override
    protected LaborergebnisObservation convertInternal(Observation resource) {
        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        BloodGasPanel bloodGasPanelBundle = new BloodGasPanel(resource);
        laborergebnisObservation.setLabortestBezeichnungDefiningCode(mapLabortestBezeichnung(bloodGasPanelBundle.getBloodGasPanel()));
        mapCarbonDioxidePartialPressureIfPresent(laborergebnisObservation, bloodGasPanelBundle.getCarbonDioxidePartialPressure());
        mapOxygenPartialPressureIfPresent(laborergebnisObservation, bloodGasPanelBundle.getOxygenPartialPressure());
        mapPhIfPresent(laborergebnisObservation, bloodGasPanelBundle.getpH());
        mapOxygenSaturationIfPresent(laborergebnisObservation, bloodGasPanelBundle.getOxygenSaturation());
        return laborergebnisObservation;
    }


    private static void mapOxygenPartialPressureIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> oxygenPartialPressure) {
        oxygenPartialPressure.ifPresent(observation -> laborergebnisObservation.setSauerstoffpartialdruck(new SauerstoffpartialdruckConverter(observation).map()));

    }

    private static void mapCarbonDioxidePartialPressureIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> carbonDioxidePartialPressure) {
        carbonDioxidePartialPressure.ifPresent(observation -> laborergebnisObservation.setKohlendioxidpartialdruck(new KohlendioxidpartialdruckConverter(observation).map()));
    }

    private static void mapPhIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> pH) {
        pH.ifPresent(observation -> laborergebnisObservation.setPhWert(new PhWertConverter(observation).map()));
    }

    private static void mapOxygenSaturationIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> oxygenSaturation){
        oxygenSaturation.ifPresent(observation -> laborergebnisObservation.setSauerstoffsaettigung(new SauerstoffsaettigungConverter(observation).map()));
    }


    private static LabortestBezeichnungDefiningCode mapLabortestBezeichnung(Observation fhirObservation){
        for (Coding coding:fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if(code.equals(LabortestBezeichnungDefiningCode.GAS_PANEL_BLOOD.getCode())){
                return LabortestBezeichnungDefiningCode.GAS_PANEL_BLOOD;
            }else if(code.equals( LabortestBezeichnungDefiningCode.GAS_PANEL_ARTERIAL_BLOOD.getCode())){
                return  LabortestBezeichnungDefiningCode.GAS_PANEL_ARTERIAL_BLOOD;
            }else if(code.equals( LabortestBezeichnungDefiningCode.GAS_PANEL_CAPILLARY_BLOOD.getCode())){
                return LabortestBezeichnungDefiningCode.GAS_PANEL_CAPILLARY_BLOOD;
            }
        }
        throw new UnprocessableEntityException("The coding of the LabortestBezeichnung: "+fhirObservation.getCode().getCoding()+" cannot be mapped, needs to be either blood (LOINC code 24338-6)" +
                ", arterial blood (24336-0) or capillary blood (24337-8), check JSON at path Observation.code.coding");
    }

}


