package org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class KoerpergewichtObservationConverter extends ObservationToObservationConverter<KoerpergewichtObservation> {
    @Override
    protected KoerpergewichtObservation convertInternal(Observation resource) {
        KoerpergewichtObservation observation = new KoerpergewichtObservation();
        observation.setGewichtMagnitude(resource.getValueQuantity().getValue().doubleValue());
        observation.setGewichtUnits(resource.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
        return observation;
    }
}
