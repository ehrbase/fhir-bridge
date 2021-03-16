package org.ehrbase.fhirbridge.ehr.converter.specific;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class RespiratoryRateCompositionConverter extends ObservationToCompositionConverter<AtemfrequenzComposition> {

    @Override
    public AtemfrequenzComposition convertInternal(@NonNull Observation resource) {
        //create result and observation objects
        AtemfrequenzComposition composition = new AtemfrequenzComposition();
        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = getStartTime(resource);
        AtemfrequenzObservation observation = new AtemfrequenzObservation();
        observation.setOriginValue(effectiveDateTime); // mandatory#
        observation.setMesswertMagnitude(resource.getValueQuantity().getValue().doubleValue());
        observation.setMesswertUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
        observation.setTimeValue(effectiveDateTime);
        observation.setLanguage(resolveLanguageOrDefault(resource));
        observation.setSubject(new PartySelf());
        composition.setAtemfrequenz(observation);

        return composition;
    }
}
