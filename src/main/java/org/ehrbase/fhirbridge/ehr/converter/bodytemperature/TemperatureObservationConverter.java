package org.ehrbase.fhirbridge.ehr.converter.bodytemperature;

import org.ehrbase.fhirbridge.ehr.converter.AbstractEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;

public class TemperatureObservationConverter extends AbstractEntryEntityConverter<Observation, KoerpertemperaturObservation> {

    @Override
    public KoerpertemperaturObservation convert(Observation resource) {
        KoerpertemperaturObservation tempObs = new KoerpertemperaturObservation();
        mapCommonAttributes(resource, tempObs);
        //TODO refactor
        tempObs.setOriginValue(resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        List<KoerpertemperaturBeliebigesEreignisChoice> events = new ArrayList<>();
        events.add(new KoerpertemperaturBeliebigesEreignisPointEventConverter().convert(resource));
        tempObs.setBeliebigesEreignis(events);
        return tempObs;
    }
}
