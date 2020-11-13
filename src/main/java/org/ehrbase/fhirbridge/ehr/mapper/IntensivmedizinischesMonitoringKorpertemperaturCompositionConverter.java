package org.ehrbase.fhirbridge.ehr.mapper;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.FeederAuditBuilder;
import org.ehrbase.fhirbridge.ehr.template.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.ehr.template.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.template.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.template.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturObservation;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.Territory;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import static java.util.Date.from;


public class IntensivmedizinischesMonitoringKorpertemperaturCompositionConverter implements CompositionConverter<IntensivmedizinischesMonitoringKorpertemperaturComposition, Observation> {

    private final Logger logger = LoggerFactory.getLogger(IntensivmedizinischesMonitoringKorpertemperaturCompositionConverter.class);

    public Observation fromComposition(IntensivmedizinischesMonitoringKorpertemperaturComposition composition) {
        Observation observation = new Observation();

        TemporalAccessor temporal;
        KorpertemperaturBeliebigesEreignisPointEvent event;
        Coding coding;


        // observations [0] . origin => effective_time
        temporal = composition.getKorpertemperatur().get(0).getOriginValue();
        observation.getEffectiveDateTimeType().setValue(from(((OffsetDateTime) temporal).toInstant()));


        // observations [0] . events [0] . value -> observation . value
        event = (KorpertemperaturBeliebigesEreignisPointEvent) composition.getKorpertemperatur().get(0).getBeliebigesEreignis().get(0);
        observation.getValueQuantity().setValue(event.getTemperaturMagnitude());
        observation.getValueQuantity().setUnit(event.getTemperaturUnits());


        // set patient
        //observation.getSubject().setReference("Patient/"+ subjectId.getValue());


        // set codes that come hardcoded in the inbound resources
        observation.getCategory().add(new CodeableConcept());
        coding = observation.getCategory().get(0).addCoding();
        coding.setSystem("http://terminology.hl7.org/CodeSystem/observation-category");
        coding.setCode("vital-signs");

        coding = observation.getCode().addCoding();
        coding.setSystem("http://loing.org");
        coding.setCode("8310-5");

        observation.setStatus(Observation.ObservationStatus.FINAL);

        observation.getMeta().addProfile(Profile.BODY_TEMP.getUri());


        // FIXME: all FHIR resources need an ID, currently we are using the compo.uid as the resource ID,
        // this is a workaround, might not work on all cases.
        observation.setId(composition.getVersionUid().toString());

        return observation;
    }

    public IntensivmedizinischesMonitoringKorpertemperaturComposition toComposition(Observation resource) {

        IntensivmedizinischesMonitoringKorpertemperaturComposition composition = new IntensivmedizinischesMonitoringKorpertemperaturComposition();

        // set feeder audit
        FeederAudit fa = new FeederAuditBuilder(resource)
                .build();
        composition.setFeederAudit(fa);


        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = resource.getEffectiveDateTimeType();


        try {
            fhirValue = resource.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            logger.debug("Value numeric: {}", fhirValueNumeric);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }

        // mapping to openEHR
        KorpertemperaturBeliebigesEreignisPointEvent tempEvent = new KorpertemperaturBeliebigesEreignisPointEvent();
        tempEvent.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempEvent.setTemperaturMagnitude(fhirValueNumeric.doubleValue());
        tempEvent.setTemperaturUnits(fhirValue.getUnit());


        KorpertemperaturObservation tempObs = new KorpertemperaturObservation();
        List<KorpertemperaturBeliebigesEreignisChoice> events = new ArrayList<>();
        events.add(tempEvent);
        tempObs.setBeliebigesEreignis(events);
        tempObs.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempObs.setLanguage(Language.EN); // FIXME: we need to grab the language from the template
        tempObs.setSubject(new PartySelf());

        List<KorpertemperaturObservation> observations = new ArrayList<>();
        observations.add(tempObs);
        composition.setKorpertemperatur(observations);

        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }
}