package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class RespiratoryRateCompositionConverter implements CompositionConverter<AtemfrequenzComposition, Observation> {

    @Override
    public AtemfrequenzComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        //create result and observation objects
        AtemfrequenzComposition result = new AtemfrequenzComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);

        AtemfrequenzObservation atemfrequenzObservation = new AtemfrequenzObservation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            atemfrequenzObservation.setOriginValue(effectiveDateTime); // mandatory#
            atemfrequenzObservation.setMesswertMagnitude(observation.getValueQuantity().getValue().doubleValue());
            atemfrequenzObservation.setMesswertUnits(observation.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            atemfrequenzObservation.setTimeValue(effectiveDateTime);
            atemfrequenzObservation.setLanguage(Language.DE);/
            atemfrequenzObservation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setAtemfrequenz(atemfrequenzObservation);

        // Required fields by API
        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setStartTimeValue(effectiveDateTime);
        result.setComposer(new PartySelf());

        return result;
    }
}
