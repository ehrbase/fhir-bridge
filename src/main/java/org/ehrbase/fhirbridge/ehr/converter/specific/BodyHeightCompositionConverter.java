package org.ehrbase.fhirbridge.ehr.converter.specific;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class BodyHeightCompositionConverter extends ObservationToCompositionConverter<KoerpergroesseComposition> {

    @Override
    public KoerpergroesseComposition convertInternal(@NonNull Observation resource) {
        KoerpergroesseComposition composition = new KoerpergroesseComposition();
        GroesseLaengeObservation observation = new GroesseLaengeObservation();
        setDateTime(observation, getStartTime(resource));
        setDefault(observation);
        setMappingContent(resource, observation);
        composition.setGroesseLaenge(observation);
        return composition;
    }

    private void setDateTime(GroesseLaengeObservation observation, ZonedDateTime startTime) {
        observation.setTimeValue(startTime);
        observation.setOriginValue(startTime);
    }

    private void setMappingContent(Observation resource, GroesseLaengeObservation observation) {
        observation.setGroesseLaengeUnits(resource.getValueQuantity().getCode());
        observation.setGroesseLaengeMagnitude(resource.getValueQuantity().getValue().doubleValue());
    }

    private void setDefault(GroesseLaengeObservation observation) {
        observation.setLanguage(Language.DE);
        observation.setSubject(new PartySelf());
    }
}
