package org.ehrbase.fhirbridge.ehr.converter.bloodgas;


import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter.KohlendioxidpartialdruckConverter;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter.PhWertConverter;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter.SauerstoffpartialdruckConverter;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter.SauerstoffsaettigungConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.LabortestBezeichnungDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

class LaborergebnisBefundConverter {


    private LaborergebnisBefundConverter() {
    }

    public static LaborergebnisObservation map(BloodGasPanel bloodGasPanelBundle) {
        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        laborergebnisObservation.setLabortestBezeichnungDefiningcode(mapLabortestBezeichnung(bloodGasPanelBundle.getBloodGasPanel()));
        laborergebnisObservation.setLanguage(Language.DE);
        laborergebnisObservation.setOriginValue(bloodGasPanelBundle.getBloodGasPanel().getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborergebnisObservation.setTimeValue(bloodGasPanelBundle.getBloodGasPanel().getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborergebnisObservation.setSubject(new PartySelf());

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
        oxygenSaturation.ifPresent(observation -> laborergebnisObservation.setSauerstoffsattigung(new SauerstoffsaettigungConverter(observation).map()));
    }


    private static LabortestBezeichnungDefiningcode mapLabortestBezeichnung(Observation fhirObservation){
        for (Coding coding:fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if(code.equals(LabortestBezeichnungDefiningcode.GAS_PANEL_BLOOD.getCode())){
                return LabortestBezeichnungDefiningcode.GAS_PANEL_BLOOD;
            }else if(code.equals( LabortestBezeichnungDefiningcode.GAS_PANEL_ARTERIAL_BLOOD.getCode())){
                return  LabortestBezeichnungDefiningcode.GAS_PANEL_ARTERIAL_BLOOD;
            }else if(code.equals( LabortestBezeichnungDefiningcode.GAS_PANEL_CAPILLARY_BLOOD.getCode())){
                return  LabortestBezeichnungDefiningcode.GAS_PANEL_CAPILLARY_BLOOD;
            }
        }
        throw new IllegalArgumentException("The coding of the LabortestBezeichnung: "+fhirObservation.getCode().getCoding()+" cannot be mapped, needs to be either blood (LOINC code 24338-6)" +
                ", arterial blood (24336-0) or capillary blood (24337-8), check JSON at path Observation.code.coding");
    }



}


