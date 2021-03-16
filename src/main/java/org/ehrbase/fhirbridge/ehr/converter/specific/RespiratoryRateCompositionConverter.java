package org.ehrbase.fhirbridge.ehr.converter.specific;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.AtemfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class RespiratoryRateCompositionConverter extends CompositionConverter<Observation, AtemfrequenzComposition> {

    @Override
    public AtemfrequenzComposition convertInternal(@NonNull Observation resource) {
        //create result and observation objects
        AtemfrequenzComposition composition = new AtemfrequenzComposition();
        AtemfrequenzObservation observation = new AtemfrequenzObservation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime;
        try {
            effectiveDateTime = resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            observation.setOriginValue(effectiveDateTime); // mandatory#
            observation.setMesswertMagnitude(resource.getValueQuantity().getValue().doubleValue());
            observation.setMesswertUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            observation.setTimeValue(effectiveDateTime);
            observation.setLanguage(Language.DE);
            observation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        composition.setAtemfrequenz(observation);

        // Required fields by API
        composition.setStartTimeValue(effectiveDateTime);
        return composition;
    }
}
