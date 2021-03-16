package org.ehrbase.fhirbridge.ehr.converter.specific;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.KoerpergewichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class BodyWeightCompositionConverter extends ObservationToCompositionConverter<KoerpergewichtComposition> {

    @Override
    protected KoerpergewichtComposition convertInternal(Observation resource) {
        KoerpergewichtComposition composition = new KoerpergewichtComposition();

        KoerpergewichtObservation observation = new KoerpergewichtObservation();
        ZonedDateTime startTime = getStartTime(resource);
        observation.setOriginValue(startTime); // mandatory#
        observation.setTimeValue(startTime);
        observation.setGewichtMagnitude(resource.getValueQuantity().getValue().doubleValue());
        observation.setGewichtUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
        observation.setLanguage(Language.DE);
        observation.setSubject(new PartySelf());

        composition.setKoerpergewicht(observation);
        return composition;
    }
}
