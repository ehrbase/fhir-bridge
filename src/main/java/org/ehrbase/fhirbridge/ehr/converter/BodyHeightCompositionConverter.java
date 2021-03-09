package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;


public class BodyHeightCompositionConverter implements CompositionConverter<KoerpergroesseComposition, Observation> {

    private static final Logger LOG = LoggerFactory.getLogger(BodyHeightCompositionConverter.class);


    @Override
    public Observation fromComposition(KoerpergroesseComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public KoerpergroesseComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        GroesseLaengeObservation grosseLangeObservation = new GroesseLaengeObservation();
        setDateTime(grosseLangeObservation, getDateTime(observation));
        setDefault(grosseLangeObservation);
        setMappingContent(observation, grosseLangeObservation);

        return createComposition(getDateTime(observation), grosseLangeObservation, observation);
    }

    private KoerpergroesseComposition createComposition(ZonedDateTime fhirEffectiveDateTime, GroesseLaengeObservation grosseLangeObservation, Observation observation) {
        KoerpergroesseComposition composition = new KoerpergroesseComposition();
        FeederAudit feederAudit = CommonData.constructFeederAudit(observation);
        composition.setFeederAudit(feederAudit);
        composition.setGroesseLaenge(grosseLangeObservation);
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setStartTimeValue(fhirEffectiveDateTime);
        composition.setComposer(new PartySelf());
        composition.setFeederAudit(CommonData.constructFeederAudit(observation));
        return (composition);
    }

    private ZonedDateTime getDateTime(Observation observation) {
        ZonedDateTime fhirEffectiveDateTime;
        if(observation.hasEffectiveDateTimeType()) {
            fhirEffectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if(observation.hasEffectivePeriod()) {
            fhirEffectiveDateTime = observation.getEffectivePeriod().getStart().toInstant().atZone(ZoneId.systemDefault());
        } else {
            throw new UnprocessableEntityException("No time is set");
        }
        return fhirEffectiveDateTime;
    }

    private void setDateTime(GroesseLaengeObservation grosseLangeObservation, ZonedDateTime fhirEffectiveDateTime) {
        grosseLangeObservation.setTimeValue(fhirEffectiveDateTime);
        grosseLangeObservation.setOriginValue(fhirEffectiveDateTime);
    }

    private void setMappingContent(Observation observation, GroesseLaengeObservation grosseLangeObservation) {
        grosseLangeObservation.setGroesseLaengeUnits(observation.getValueQuantity().getCode());
        grosseLangeObservation.setGroesseLaengeMagnitude(observation.getValueQuantity().getValue().doubleValue());
    }

    private void setDefault(GroesseLaengeObservation grosseLangeObservation) {
        grosseLangeObservation.setLanguage(Language.DE);
        grosseLangeObservation.setSubject(new PartySelf());
    }
}
