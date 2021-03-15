package org.ehrbase.fhirbridge.ehr.converter.heartrate;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.AbstractEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class HerzfrequenzConverter extends AbstractEntryEntityConverter<Observation, HerzfrequenzObservation> {

    @Override
    public HerzfrequenzObservation convert(Observation resource) {
        HerzfrequenzObservation herzfrequenzObservation = new HerzfrequenzObservation();
        mapCommonAttributes(resource,herzfrequenzObservation);
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
