package org.ehrbase.fhirbridge.ehr.converter.specificconverters.bodytemperature;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;
import java.util.ArrayList;
import java.util.List;

public class BodyTemperatureCompositionConverter extends ObservationToCompositionConverter<IntensivmedizinischesMonitoringKorpertemperaturComposition> {

    @Override
    public IntensivmedizinischesMonitoringKorpertemperaturComposition convertInternal(@NonNull Observation resource) {
        IntensivmedizinischesMonitoringKorpertemperaturComposition composition = new IntensivmedizinischesMonitoringKorpertemperaturComposition();
        List<KoerpertemperaturObservation> observations = new ArrayList<>();
        observations.add(new TemperatureObservationConverter().convert(resource));
        composition.setKoerpertemperatur(observations);
        composition.setComposer(new PartySelf());
        return composition;
    }
}
