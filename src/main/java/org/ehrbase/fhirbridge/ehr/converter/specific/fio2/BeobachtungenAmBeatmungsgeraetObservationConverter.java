package org.ehrbase.fhirbridge.ehr.converter.specific.fio2;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeraetObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class BeobachtungenAmBeatmungsgeraetObservationConverter extends ObservationToObservationConverter<BeobachtungenAmBeatmungsgeraetObservation> {

    private static final Logger LOG = LoggerFactory.getLogger(BeobachtungenAmBeatmungsgeraetObservationConverter.class);

    @Override
    protected BeobachtungenAmBeatmungsgeraetObservation convertInternal(Observation resource) {
        BeobachtungenAmBeatmungsgeraetObservation observation = new BeobachtungenAmBeatmungsgeraetObservation();
        EingeatmeterSauerstoffCluster eingeatmeterSauerstoff = new EingeatmeterSauerstoffCluster();
        if(resource.hasValueQuantity()){
            mapNumerator(resource).ifPresent(eingeatmeterSauerstoff::setInspiratorischeSauerstofffraktion);
            observation.setEingeatmeterSauerstoff(eingeatmeterSauerstoff);
        }else if(resource.hasValueCodeableConcept()){
            LOG.warn("Entering only value[x].ValueCodeableConcept makes mapping of an value impossible, since not static coding is defined by the fhir resource. Please use ValueQuantity instead. Therefore an empty field is mapped");
        }else{
            observation.setEingeatmeterSauerstoff(getNullflavour());
        }
        return observation;
    }

    private EingeatmeterSauerstoffCluster getNullflavour() {
        EingeatmeterSauerstoffCluster eingeatmeterSauerstoff = new EingeatmeterSauerstoffCluster();
        eingeatmeterSauerstoff.setInspiratorischeSauerstofffraktionNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        return eingeatmeterSauerstoff;
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
