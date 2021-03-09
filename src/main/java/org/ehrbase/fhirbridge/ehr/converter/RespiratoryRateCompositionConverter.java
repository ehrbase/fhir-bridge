package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class RespiratoryRateCompositionConverter extends AbstractCompositionConverter<Observation, AtemfrequenzComposition> {

    @Override
    public AtemfrequenzComposition convert(@NonNull Observation observation) {
        //create result and observation objects
        AtemfrequenzComposition result = new AtemfrequenzComposition();
        mapCommonAttributes(observation, result);

        AtemfrequenzObservation atemfrequenzObservation = new AtemfrequenzObservation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            atemfrequenzObservation.setOriginValue(effectiveDateTime); // mandatory#
            atemfrequenzObservation.setMesswertMagnitude(observation.getValueQuantity().getValue().doubleValue());
            atemfrequenzObservation.setMesswertUnits(observation.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            atemfrequenzObservation.setTimeValue(effectiveDateTime);
            atemfrequenzObservation.setLanguage(Language.DE);
            atemfrequenzObservation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setAtemfrequenz(atemfrequenzObservation);

        // Required fields by API
        result.setStartTimeValue(effectiveDateTime);
        mapCommonAttributes(observation, result);
        return result;
    }
}
