package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.KoerpergewichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class BodyWeightCompositionConverter extends AbstractCompositionConverter<Observation, KoerpergewichtComposition> {

    @Override
    public KoerpergewichtComposition convert(@NonNull Observation observation) {
        KoerpergewichtComposition result = new KoerpergewichtComposition();
        KoerpergewichtObservation KoerpergewichtObservation = new KoerpergewichtObservation();
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            KoerpergewichtObservation.setOriginValue(effectiveDateTime); // mandatory#
            KoerpergewichtObservation.setGewichtMagnitude(observation.getValueQuantity().getValue().doubleValue());
            KoerpergewichtObservation.setGewichtUnits(observation.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            KoerpergewichtObservation.setTimeValue(effectiveDateTime);
            KoerpergewichtObservation.setLanguage(Language.DE);
            KoerpergewichtObservation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        result.setKoerpergewicht(KoerpergewichtObservation);
        result.setStartTimeValue(effectiveDateTime);
        return result;
    }
}
