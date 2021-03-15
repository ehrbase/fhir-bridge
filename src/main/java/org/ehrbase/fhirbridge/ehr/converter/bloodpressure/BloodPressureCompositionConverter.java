package org.ehrbase.fhirbridge.ehr.converter.bloodpressure;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BloodPressureCompositionConverter extends AbstractCompositionConverter<Observation, BlutdruckComposition> {

    @Override
    public BlutdruckComposition convert(@NonNull Observation observation) {
        BlutdruckComposition result = new BlutdruckComposition();
        mapCommonAttributes(observation, result);
        result.setBlutdruck(new BlutdruckObservationConverter().convert(observation));
        //TODO refactor
        result.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        return result;
    }
}
