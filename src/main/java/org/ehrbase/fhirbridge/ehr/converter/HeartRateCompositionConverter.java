package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class HeartRateCompositionConverter extends AbstractCompositionConverter<Observation, HerzfrequenzComposition> {

    @Override
    public HerzfrequenzComposition convert(@NotNull Observation observation) {
        //create result and observation objects
        HerzfrequenzComposition result = new HerzfrequenzComposition();

        HerzfrequenzObservation herzfrequenzObservation = new HerzfrequenzObservation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            herzfrequenzObservation.setOriginValue(effectiveDateTime); // mandatory#
            herzfrequenzObservation.setFrequenzMagnitude(observation.getValueQuantity().getValue().doubleValue());
            herzfrequenzObservation.setFrequenzUnits(observation.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            herzfrequenzObservation.setTimeValue(effectiveDateTime);
            herzfrequenzObservation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setHerzfrequenz(herzfrequenzObservation);

        // Required fields by API
        result.setStartTimeValue(effectiveDateTime);

        return result;
    }
}
