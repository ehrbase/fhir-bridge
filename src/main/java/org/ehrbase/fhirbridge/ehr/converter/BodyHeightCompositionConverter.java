package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BodyHeightCompositionConverter extends AbstractCompositionConverter<Observation, KoerpergroesseComposition> {

    @Override
    public KoerpergroesseComposition convert(@NonNull Observation observation) {

        GroesseLaengeObservation grosseLangeObservation = new GroesseLaengeObservation();
        setDateTime(grosseLangeObservation, getDateTime(observation));
        setDefault(grosseLangeObservation);
        setMappingContent(observation, grosseLangeObservation);

        return createComposition(getDateTime(observation), grosseLangeObservation, observation);
    }

    private KoerpergroesseComposition createComposition(ZonedDateTime fhirEffectiveDateTime, GroesseLaengeObservation grosseLangeObservation, Observation observation) {
        KoerpergroesseComposition composition = new KoerpergroesseComposition();
        mapCommonAttributes(observation, composition);
        composition.setGroesseLaenge(grosseLangeObservation);
        composition.setStartTimeValue(fhirEffectiveDateTime);
        return (composition);
    }

    private ZonedDateTime getDateTime(Observation observation) {
        ZonedDateTime fhirEffectiveDateTime;
        if (observation.hasEffectiveDateTimeType()) {
            fhirEffectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else if (observation.hasEffectivePeriod()) {
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
        grosseLangeObservation.setSubject(new PartySelf());
    }
}
