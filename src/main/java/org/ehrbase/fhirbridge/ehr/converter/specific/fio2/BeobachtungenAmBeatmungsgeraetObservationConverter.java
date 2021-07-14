package org.ehrbase.fhirbridge.ehr.converter.specific.fio2;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeraetObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class BeobachtungenAmBeatmungsgeraetObservationConverter extends ObservationToObservationConverter<BeobachtungenAmBeatmungsgeraetObservation> {
    @Override
    protected BeobachtungenAmBeatmungsgeraetObservation convertInternal(Observation resource) {
        BeobachtungenAmBeatmungsgeraetObservation observation = new BeobachtungenAmBeatmungsgeraetObservation();
        EingeatmeterSauerstoffCluster eingeatmeterSauerstoff = new EingeatmeterSauerstoffCluster();
        mapNumerator(resource).ifPresent(eingeatmeterSauerstoff::setInspiratorischeSauerstofffraktion);
        observation.setEingeatmeterSauerstoff(eingeatmeterSauerstoff);
        return observation;
    }

    private Optional<DvProportion> mapNumerator(Observation resource) {
        if (resource.hasValueQuantity()) {
            DvProportion inspiratorischeSauerstofffraktion = new DvProportion();
            inspiratorischeSauerstofffraktion.setNumerator(resource.getValueQuantity().getValue().doubleValue());
            inspiratorischeSauerstofffraktion.setDenominator(100.0);
            inspiratorischeSauerstofffraktion.setType((long) 2);//2=percent (https://specifications.openehr.org/releases/RM/latest/data_types.html#_proportion_kind_class)
            return Optional.of(inspiratorischeSauerstofffraktion);
        }
        return Optional.empty();
    }
}
