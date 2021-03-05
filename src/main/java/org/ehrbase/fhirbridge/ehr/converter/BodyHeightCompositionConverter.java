package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;


public class BodyHeightCompositionConverter implements CompositionConverter<KorpergrosseComposition, Observation> {

    private static final Logger LOG = LoggerFactory.getLogger(BodyHeightCompositionConverter.class);


    @Override
    public Observation fromComposition(KorpergrosseComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public KorpergrosseComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        GrosseLangeObservation grosseLangeObservation = new GrosseLangeObservation();
        setDateTime(grosseLangeObservation, getDateTime(observation));
        setDefault(grosseLangeObservation);
        setMappingContent(observation, grosseLangeObservation);

        return createComposition(getDateTime(observation), grosseLangeObservation, observation);
    }

    private KorpergrosseComposition createComposition(ZonedDateTime fhirEffectiveDateTime, GrosseLangeObservation grosseLangeObservation, Observation observation) {
        KorpergrosseComposition composition = new KorpergrosseComposition();
        composition.setGrosseLange(grosseLangeObservation);
        // Required fields by API
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirEffectiveDateTime);
        composition.setComposer(new PartySelf());

        composition.setFeederAudit(CommonData.constructFeederAudit(observation));

        return (composition);
    }

    private ZonedDateTime getDateTime(Observation observation) {
        ZonedDateTime fhirEffectiveDateTime;
        if(observation.hasEffectiveDateTimeType()) {

            // default for every observation
            fhirEffectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();


        } else if(observation.hasEffectivePeriod()) {
            fhirEffectiveDateTime = observation.getEffectivePeriod().getStart().toInstant().atZone(ZoneId.systemDefault());

        } else {
            throw new UnprocessableEntityException("No time is set");
        }
        return fhirEffectiveDateTime;
    }

    private void setDateTime(GrosseLangeObservation grosseLangeObservation, ZonedDateTime fhirEffectiveDateTime) {
        grosseLangeObservation.setTimeValue(fhirEffectiveDateTime);
        grosseLangeObservation.setOriginValue(fhirEffectiveDateTime);
    }

    private void setMappingContent(Observation observation, GrosseLangeObservation grosseLangeObservation) {
        grosseLangeObservation.setGrosseLangeUnits(observation.getValueQuantity().getCode());
        grosseLangeObservation.setGrosseLangeMagnitude(observation.getValueQuantity().getValue().doubleValue());
    }

    private void setDefault(GrosseLangeObservation grosseLangeObservation) {
        grosseLangeObservation.setLanguage(Language.DE);
        grosseLangeObservation.setSubject(new PartySelf());
    }
}
