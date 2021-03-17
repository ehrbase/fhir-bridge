package org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight;

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
        composition.setKoerpergewicht(new KoerpergewichtObservationConverter().convert(resource));
        return composition;
    }
}
