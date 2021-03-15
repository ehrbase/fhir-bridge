package org.ehrbase.fhirbridge.ehr.converter.bodytemperature;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
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

public class BodyTemperatureCompositionConverter extends AbstractCompositionConverter<Observation, IntensivmedizinischesMonitoringKorpertemperaturComposition> {


    @Override
    public IntensivmedizinischesMonitoringKorpertemperaturComposition convert(@NonNull Observation observation) {
        IntensivmedizinischesMonitoringKorpertemperaturComposition result = new IntensivmedizinischesMonitoringKorpertemperaturComposition();
        mapCommonAttributes(observation, result);

        // ========================================================================================
        // value quantity is expected

        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();


        // mapping to openEHR

        List<KoerpertemperaturObservation> observations = new ArrayList<>();
        observations.add(new TemperatureObservationConverter().convert(observation));
        result.setKoerpertemperatur(observations);
        // ======================================================================================
        // Required fields by API
        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return result;
    }
}
