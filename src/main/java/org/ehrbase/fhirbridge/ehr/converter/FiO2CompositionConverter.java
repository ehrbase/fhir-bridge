package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeraetObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public class FiO2CompositionConverter extends AbstractCompositionConverter<Observation, BeatmungswerteComposition> {

    @Override
    public BeatmungswerteComposition convert(@NonNull Observation observation) {
        BeatmungswerteComposition result = new BeatmungswerteComposition();
        mapCommonAttributes(observation, result);

        BeobachtungenAmBeatmungsgeraetObservation beobachtungenAmBeatmungsgeraetObservation = new BeobachtungenAmBeatmungsgeraetObservation();
        ZonedDateTime effectiveDateTime = null;
        EingeatmeterSauerstoffCluster eingeatmeterSauerstoff = new EingeatmeterSauerstoffCluster();
        DvProportion inspiratorischeSauerstofffraktion = new DvProportion();

        try {
            effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            beobachtungenAmBeatmungsgeraetObservation.setOriginValue(effectiveDateTime); // mandatory
            beobachtungenAmBeatmungsgeraetObservation.setTimeValue(effectiveDateTime);
            beobachtungenAmBeatmungsgeraetObservation.setLanguage(Language.DE);
            beobachtungenAmBeatmungsgeraetObservation.setSubject(new PartySelf());
            inspiratorischeSauerstofffraktion.setNumerator(observation.getValueQuantity().getValue().doubleValue());
            inspiratorischeSauerstofffraktion.setDenominator(100.0);
            inspiratorischeSauerstofffraktion.setType((long) 2);//2=percent (https://specifications.openehr.org/releases/RM/latest/data_types.html#_proportion_kind_class)
            eingeatmeterSauerstoff.setInspiratorischeSauerstofffraktion(inspiratorischeSauerstofffraktion);
            beobachtungenAmBeatmungsgeraetObservation.setEingeatmeterSauerstoff(eingeatmeterSauerstoff);
            result.setBeobachtungenAmBeatmungsgeraet(beobachtungenAmBeatmungsgeraetObservation);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setStartTimeValue(effectiveDateTime);
        return result;
    }
}
