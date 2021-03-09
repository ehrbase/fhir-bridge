package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.KoerpergewichtComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;

import java.time.ZonedDateTime;

public class BodyWeightCompositionConverter implements CompositionConverter<KoerpergewichtComposition, Observation> {

    @Override
    public Observation fromComposition(KoerpergewichtComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public KoerpergewichtComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }
        KoerpergewichtComposition result = new KoerpergewichtComposition();
        FeederAudit feederAudit = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(feederAudit);
        KoerpergewichtObservation KoerpergewichtObservation = new KoerpergewichtObservation();
        ZonedDateTime effectiveDateTime = null;
        try {
            effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
            KoerpergewichtObservation.setOriginValue(effectiveDateTime); // mandatory#
            KoerpergewichtObservation.setGewichtMagnitude(observation.getValueQuantity().getValue().doubleValue());
            KoerpergewichtObservation.setGewichtUnits(observation.getValueQuantity().getCode());//note that the textual value that openEHR template expects as unit is stored in code for this entity
            KoerpergewichtObservation.setTimeValue(effectiveDateTime);
            KoerpergewichtObservation.setLanguage(Language.DE);
            KoerpergewichtObservation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        result.setKoerpergewicht(KoerpergewichtObservation);
        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setStartTimeValue(effectiveDateTime);
        result.setComposer(new PartySelf());
        return result;
    }
}
