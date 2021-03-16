package org.ehrbase.fhirbridge.ehr.converter.specific.heartrate;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class HerzfrequenzObservationConverter extends ObservationToObservationConverter<HerzfrequenzObservation> {

    @Override
    protected HerzfrequenzObservation convertInternal(Observation resource) {
        HerzfrequenzObservation herzfrequenzObservation = new HerzfrequenzObservation();
        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
            //TODO refactor time values
            effectiveDateTime = resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            herzfrequenzObservation.setOriginValue(effectiveDateTime); // mandatory#
            herzfrequenzObservation.setFrequenzMagnitude(resource.getValueQuantity().getValue().doubleValue());
            herzfrequenzObservation.setFrequenzUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            herzfrequenzObservation.setTimeValue(effectiveDateTime);
            herzfrequenzObservation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        return herzfrequenzObservation;
    }
}
