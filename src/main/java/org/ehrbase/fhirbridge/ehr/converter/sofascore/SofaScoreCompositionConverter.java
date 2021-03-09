package org.ehrbase.fhirbridge.ehr.converter.sofascore;


import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class SofaScoreCompositionConverter implements CompositionConverter<SOFAComposition, Observation> {
    private static final Logger LOG = LoggerFactory.getLogger(SofaScoreCompositionConverter.class);

    @Override
    public SOFAComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        SOFAComposition result = new SOFAComposition();
        FeederAudit feederAudit = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(feederAudit);

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);


        result.setSofaScore(new SofaScoreObservationConverter().convert(observation));


        // ======================================================================================
        // Required fields by API
        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        mapTimeDate(observation, result);

        result.setComposer(new PartySelf());

        return result;
    }

    private void mapTimeDate(Observation observation, SOFAComposition result) {
        tryEffectiveDateTime(observation, result);
        tryEffectiveInstantType(observation, result);
        tryEffectivePeriodType(observation, result);
    }

    private void tryEffectiveDateTime(Observation observation, SOFAComposition result) {
        try{
            result.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        }catch (FHIRException fhirException){
            if(isTimeTypeException(fhirException.toString())){
                throw fhirException;
            }
        }
    }

    private void tryEffectiveInstantType(Observation observation, SOFAComposition result) {
        try{
            result.setStartTimeValue(observation.getEffectiveInstantType().getValueAsCalendar().toZonedDateTime());
        }catch (FHIRException fhirException){
            if(isTimeTypeException(fhirException.toString())){
                throw fhirException;
            }
        }
    }

    private void tryEffectivePeriodType(Observation observation, SOFAComposition result) {
        try{
            LocalDateTime date = LocalDateTime.ofInstant(observation.getEffectivePeriod().getStart().toInstant(), ZoneOffset.UTC);
            result.setStartTimeValue(date);
            result.setEndTimeValue(date);
        }catch (FHIRException fhirException){
            if(isTimeTypeException(fhirException.toString())){
                throw fhirException;
            }
        }
    }

    private boolean isTimeTypeException(String exceptionMessage){
        return !(exceptionMessage.contains("Type mismatch: the type") && exceptionMessage.contains("was expected,") && exceptionMessage.contains("was encountered"));
    }

}
