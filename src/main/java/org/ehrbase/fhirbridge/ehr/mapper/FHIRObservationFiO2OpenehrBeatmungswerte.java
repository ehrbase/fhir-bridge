package org.ehrbase.fhirbridge.mapping;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.fhirbridge.ehr.mapper.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeratObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
import org.hl7.fhir.r4.model.*;
import java.time.ZonedDateTime;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

public class FHIRObservationFiO2OpenehrBeatmungswerte {
    private FHIRObservationFiO2OpenehrBeatmungswerte() {}
    
    public static BeatmungswerteComposition map(Observation fhirObservation) {
        
        BeatmungswerteComposition composition = new BeatmungswerteComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(fhirObservation);
        composition.setFeederAudit(fa);


        BeobachtungenAmBeatmungsgeratObservation observation = new BeobachtungenAmBeatmungsgeratObservation();
        ZonedDateTime effectiveDateTime = null;
        EingeatmeterSauerstoffCluster eingeatmeterSauerstoff = new EingeatmeterSauerstoffCluster();
        DvProportion inspiratorischeSauerstofffraktion = new DvProportion();
        
        //map values of interest from FHIR observation
        try {
            //obligatory stuff block
            effectiveDateTime = fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            observation.setOriginValue(effectiveDateTime); // mandatory
            observation.setTimeValue(effectiveDateTime);
            observation.setLanguage(Language.DE);// FIXME: we need to grab the language from the template
            observation.setSubject(new PartySelf());
            
            //inspiratorische Sauerstofffraktion numerator and denominator
            inspiratorischeSauerstofffraktion.setNumerator(fhirObservation.getValueQuantity().getValue().doubleValue());
            inspiratorischeSauerstofffraktion.setDenominator(100.0);
            inspiratorischeSauerstofffraktion.setType((long) 2);//2=percent (https://specifications.openehr.org/releases/RM/latest/data_types.html#_proportion_kind_class)
            eingeatmeterSauerstoff.setInspiratorischeSauerstofffraktion(inspiratorischeSauerstofffraktion);
            
            //compose composition
            observation.setEingeatmeterSauerstoff(eingeatmeterSauerstoff);
            composition.setBeobachtungenAmBeatmungsgerat(observation);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        
        //obligatory stuff block
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test"); //FIXME: sensible value
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE); 
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(effectiveDateTime);
        composition.setComposer(new PartySelf()); //FIXME: sensible value
        return composition;
    }

}
