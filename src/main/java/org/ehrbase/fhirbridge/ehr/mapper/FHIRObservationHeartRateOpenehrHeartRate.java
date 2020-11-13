package org.ehrbase.fhirbridge.mapping;

import java.time.ZonedDateTime;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.fhirbridge.ehr.mapper.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;

import com.nedap.archie.rm.generic.PartySelf;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

public class FHIRObservationHeartRateOpenehrHeartRate {
    private FHIRObservationHeartRateOpenehrHeartRate() {}

    public static HerzfrequenzComposition map(Observation fhirObservation) {

        //create composition and observation objects
        HerzfrequenzComposition composition = new HerzfrequenzComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(fhirObservation);
        composition.setFeederAudit(fa);

        HerzfrequenzObservation observation = new HerzfrequenzObservation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            observation.setOriginValue(effectiveDateTime); // mandatory#
            observation.setFrequenzMagnitude(fhirObservation.getValueQuantity().getValue().doubleValue());
            observation.setFrequenzUnits(fhirObservation.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            observation.setTimeValue(effectiveDateTime);
            observation.setLanguage(Language.DE);// FIXME: we need to grab the language from the template
            observation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        composition.setHerzfrequenz(observation);

        // Required fields by API
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
