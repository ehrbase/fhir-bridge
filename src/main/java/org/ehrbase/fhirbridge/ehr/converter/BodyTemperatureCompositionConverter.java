package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturObservation;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Date.from;

public class BodyTemperatureCompositionConverter implements CompositionConverter<IntensivmedizinischesMonitoringKorpertemperaturComposition, Observation> {

    private static final Logger LOG = LoggerFactory.getLogger(BodyTemperatureCompositionConverter.class);

    @Override
    public Observation fromComposition(IntensivmedizinischesMonitoringKorpertemperaturComposition composition) {
        if (composition == null) {
            return null;
        }
        Observation result = new Observation();
        TemporalAccessor temporal;
        KoerpertemperaturBeliebigesEreignisPointEvent event;
        Coding coding;
        // observations [0] . origin => effective_time
        temporal = composition.getKoerpertemperatur().get(0).getOriginValue();
        result.getEffectiveDateTimeType().setValue(Date.from(Instant.from(temporal)));
        // observations [0] . events [0] . value -> result . value
        event = (KoerpertemperaturBeliebigesEreignisPointEvent) composition.getKoerpertemperatur().get(0).getBeliebigesEreignis().get(0);
        result.getValueQuantity().setValue(event.getTemperaturMagnitude());
        result.getValueQuantity().setUnit(event.getTemperaturUnits());
        result.getCategory().add(new CodeableConcept());
        coding = result.getCategory().get(0).addCoding();
        coding.setSystem("http://terminology.hl7.org/CodeSystem/result-category");
        coding.setCode("vital-signs");

        coding = result.getCode().addCoding();
        coding.setSystem("http://loing.org");
        coding.setCode("8310-5");

        result.setStatus(Observation.ObservationStatus.FINAL);

        result.getMeta().addProfile(Profile.BODY_TEMP.getUri());
        result.setId(composition.getVersionUid().toString());

        return result;
    }

    @Override
    public IntensivmedizinischesMonitoringKorpertemperaturComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        IntensivmedizinischesMonitoringKorpertemperaturComposition result = new IntensivmedizinischesMonitoringKorpertemperaturComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);


        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();


        try {
            fhirValue = observation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            LOG.debug("Value numeric: {}", fhirValueNumeric);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }

        // mapping to openEHR
        KoerpertemperaturBeliebigesEreignisPointEvent tempEvent = new KoerpertemperaturBeliebigesEreignisPointEvent();
        tempEvent.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempEvent.setTemperaturMagnitude(fhirValueNumeric.doubleValue());
        tempEvent.setTemperaturUnits(fhirValue.getUnit());


        KoerpertemperaturObservation tempObs = new KoerpertemperaturObservation();
        List<KoerpertemperaturBeliebigesEreignisChoice> events = new ArrayList<>();
        events.add(tempEvent);
        tempObs.setBeliebigesEreignis(events);
        tempObs.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempObs.setLanguage(Language.DE);
        tempObs.setSubject(new PartySelf());

        List<KoerpertemperaturObservation> observations = new ArrayList<>();
        observations.add(tempObs);
        result.setKoerpertemperatur(observations);

        // ======================================================================================
        // Required fields by API
        result.setLanguage(Language.EN);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        result.setComposer(new PartySelf());

        return result;
    }
}