package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BodyTemperatureCompositionConverter extends CompositionConverter<Observation, IntensivmedizinischesMonitoringKorpertemperaturComposition> {

    private static final Logger LOG = LoggerFactory.getLogger(BodyTemperatureCompositionConverter.class);

    @Override
    public IntensivmedizinischesMonitoringKorpertemperaturComposition convertInternal(@NonNull Observation resource) {
        IntensivmedizinischesMonitoringKorpertemperaturComposition composition = new IntensivmedizinischesMonitoringKorpertemperaturComposition();

        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue;
        BigDecimal fhirValueNumeric;
        DateTimeType fhirEffectiveDateTime = resource.getEffectiveDateTimeType();

        try {
            fhirValue = resource.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            LOG.debug("Value numeric: {}", fhirValueNumeric);
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new ConversionException("Value is required in FHIR Observation and should be Quantity");
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
        tempObs.setSubject(new PartySelf());
        tempObs.setLanguage(Language.DE);

        List<KoerpertemperaturObservation> observations = new ArrayList<>();
        observations.add(tempObs);
        composition.setKoerpertemperatur(observations);

        // ======================================================================================
        // Required fields by API
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }
}
