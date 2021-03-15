package org.ehrbase.fhirbridge.ehr.converter.heartrate;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class HeartRateCompositionConverter extends AbstractCompositionConverter<Observation, HerzfrequenzComposition> {

    @Override
    public HerzfrequenzComposition convert(@NonNull Observation observation) {
        HerzfrequenzComposition result = new HerzfrequenzComposition();
        mapCommonAttributes(observation, result);
        result.setHerzfrequenz(new HerzfrequenzConverter().convert(observation));
        //TODO refactor
        result.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        return result;
    }
}
