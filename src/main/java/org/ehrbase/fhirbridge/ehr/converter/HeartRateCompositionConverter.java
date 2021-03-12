package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.HerzfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class HeartRateCompositionConverter extends CompositionConverter<Observation, HerzfrequenzComposition> {

    @Override
    public HerzfrequenzComposition convertInternal(@NonNull Observation resource) {
        //create result and observation objects
        HerzfrequenzComposition composition = new HerzfrequenzComposition();
        HerzfrequenzObservation observation = new HerzfrequenzObservation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            observation.setOriginValue(effectiveDateTime); // mandatory#
            observation.setFrequenzMagnitude(resource.getValueQuantity().getValue().doubleValue());
            observation.setFrequenzUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            observation.setTimeValue(effectiveDateTime);
            observation.setSubject(new PartySelf());
            observation.setLanguage(Language.DE);
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        composition.setHerzfrequenz(observation);

        // Required fields by API
        composition.setStartTimeValue(effectiveDateTime);

        return composition;
    }
}
