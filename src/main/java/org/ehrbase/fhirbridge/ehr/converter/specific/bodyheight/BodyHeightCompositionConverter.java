package org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BodyHeightCompositionConverter extends ObservationToCompositionConverter<KoerpergroesseComposition> {

    @Override
    public KoerpergroesseComposition convertInternal(@NonNull Observation resource) {
        KoerpergroesseComposition composition = new KoerpergroesseComposition();
        composition.setGroesseLaenge(new GroesseLaengeObservationConverter().convert(resource));
        return composition;
    }

}
