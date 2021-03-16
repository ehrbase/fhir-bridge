package org.ehrbase.fhirbridge.ehr.converter.specific;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BodyHeightCompositionConverter extends CompositionConverter<Observation, KoerpergroesseComposition> {

    @Override
    public KoerpergroesseComposition convertInternal(@NonNull Observation resource) {

        GroesseLaengeObservation observation = new GroesseLaengeObservation();
        setDateTime(observation, getDateTime(resource));
        setDefault(observation);
        setMappingContent(resource, observation);

        return createComposition(getDateTime(resource), observation, resource);
    }

    private KoerpergroesseComposition createComposition(ZonedDateTime fhirEffectiveDateTime, GroesseLaengeObservation grosseLangeObservation, Observation observation) {
        KoerpergroesseComposition composition = new KoerpergroesseComposition();
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
            throw new ConversionException("No time is set");
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
