package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeraetObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class FiO2CompositionConverter extends CompositionConverter<Observation, BeatmungswerteComposition> {

    @Override
    public BeatmungswerteComposition convertInternal(@NonNull Observation resource) {
        BeatmungswerteComposition composition = new BeatmungswerteComposition();

        BeobachtungenAmBeatmungsgeraetObservation observation = new BeobachtungenAmBeatmungsgeraetObservation();
        ZonedDateTime effectiveDateTime;
        EingeatmeterSauerstoffCluster eingeatmeterSauerstoff = new EingeatmeterSauerstoffCluster();
        DvProportion inspiratorischeSauerstofffraktion = new DvProportion();

        try {
            effectiveDateTime = resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            observation.setOriginValue(effectiveDateTime); // mandatory
            observation.setTimeValue(effectiveDateTime);
            observation.setLanguage(Language.DE);
            observation.setSubject(new PartySelf());
            inspiratorischeSauerstofffraktion.setNumerator(resource.getValueQuantity().getValue().doubleValue());
            inspiratorischeSauerstofffraktion.setDenominator(100.0);
            inspiratorischeSauerstofffraktion.setType((long) 2);//2=percent (https://specifications.openehr.org/releases/RM/latest/data_types.html#_proportion_kind_class)
            eingeatmeterSauerstoff.setInspiratorischeSauerstofffraktion(inspiratorischeSauerstofffraktion);
            observation.setEingeatmeterSauerstoff(eingeatmeterSauerstoff);
            composition.setBeobachtungenAmBeatmungsgeraet(observation);
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        composition.setStartTimeValue(effectiveDateTime);
        return composition;
    }
}
