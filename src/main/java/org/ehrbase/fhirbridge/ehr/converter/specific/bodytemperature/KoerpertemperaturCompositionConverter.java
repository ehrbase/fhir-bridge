package org.ehrbase.fhirbridge.ehr.converter.specific.bodytemperature;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.KoerpertemperaturComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class KoerpertemperaturCompositionConverter extends ObservationToCompositionConverter<KoerpertemperaturComposition> {

    @Override
    public KoerpertemperaturComposition convertInternal(@NonNull Observation resource) {
        KoerpertemperaturComposition composition = new KoerpertemperaturComposition();
        composition.setKoerpertemperatur(new KoerpertemperaturObservationConverter().convert(resource));
        return composition;
    }
}
