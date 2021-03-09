package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.BeatmungswerteComposition;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.BeobachtungenAmBeatmungsgeraetObservation;
import org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition.EingeatmeterSauerstoffCluster;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class FiO2CompositionConverter implements CompositionConverter<BeatmungswerteComposition, Observation> {

    @Override
    public Observation fromComposition(BeatmungswerteComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public BeatmungswerteComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        BeatmungswerteComposition result = new BeatmungswerteComposition();
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);

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
        result.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        result.setLocation("test"); //FIXME: sensible value
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setStartTimeValue(effectiveDateTime);
        result.setComposer(new PartySelf()); //FIXME: sensible value
        return result;
    }
}
