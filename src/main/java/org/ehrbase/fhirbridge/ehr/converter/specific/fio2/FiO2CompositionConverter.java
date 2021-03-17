package org.ehrbase.fhirbridge.ehr.converter.specific.fio2;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeraetObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class FiO2CompositionConverter extends ObservationToCompositionConverter<BeatmungswerteComposition> {

    @Override
    public BeatmungswerteComposition convertInternal(@NonNull Observation resource) {
        BeatmungswerteComposition composition = new BeatmungswerteComposition();
        composition.setBeobachtungenAmBeatmungsgeraet(new BeobachtungenAmBeatmungsgeraetObservationConverter().convert(resource));
        return composition;
    }
}
