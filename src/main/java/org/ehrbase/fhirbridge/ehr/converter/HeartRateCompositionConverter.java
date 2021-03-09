package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class HeartRateCompositionConverter implements CompositionConverter<HerzfrequenzComposition, Observation> {

    @Override
    public Observation fromComposition(HerzfrequenzComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public HerzfrequenzComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        //create result and observation objects
        HerzfrequenzComposition result = new HerzfrequenzComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);

        HerzfrequenzObservation herzfrequenzObservation = new HerzfrequenzObservation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            herzfrequenzObservation.setOriginValue(effectiveDateTime); // mandatory#
            herzfrequenzObservation.setFrequenzMagnitude(observation.getValueQuantity().getValue().doubleValue());
            herzfrequenzObservation.setFrequenzUnits(observation.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            herzfrequenzObservation.setTimeValue(effectiveDateTime);
            herzfrequenzObservation.setLanguage(Language.DE);// FIXME: we need to grab the language from the template
            herzfrequenzObservation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setHerzfrequenz(herzfrequenzObservation);

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
