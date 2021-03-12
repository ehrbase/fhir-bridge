package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.KoerpergewichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class BodyWeightCompositionConverter extends CompositionConverter<Observation, KoerpergewichtComposition> {

    @Override
    protected KoerpergewichtComposition convertInternal(Observation resource) {
        KoerpergewichtComposition composition = new KoerpergewichtComposition();
        KoerpergewichtObservation observation = new KoerpergewichtObservation();

        ZonedDateTime effectiveDateTime;
        try {
            effectiveDateTime = resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            observation.setOriginValue(effectiveDateTime); // mandatory#
            observation.setGewichtMagnitude(resource.getValueQuantity().getValue().doubleValue());
            observation.setGewichtUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            observation.setTimeValue(effectiveDateTime);
            observation.setLanguage(Language.DE);
            observation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        composition.setKoerpergewicht(observation);
        composition.setStartTimeValue(effectiveDateTime);

        return composition;
    }
}
