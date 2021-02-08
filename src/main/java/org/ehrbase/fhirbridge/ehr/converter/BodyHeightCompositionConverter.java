package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.fhirbridge.ehr.opt.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BodyHeightCompositionConverter implements CompositionConverter<KorpergrosseComposition, Observation> {

    private static final Logger LOG = LoggerFactory.getLogger(BodyHeightCompositionConverter.class);

    @Override
    public Observation fromComposition(KorpergrosseComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public KorpergrosseComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        // references:
        // https://ckm.highmed.org/ckm/templates/1246.169.1038
        // https://tools.openehr.org/designer/?code=39ddef9b1e993dc441ff#/designer/repos/num-2021-01-11
        // https://simplifier.net/ForschungsnetzCovid-19/BodyHeight/~overview
        // https://simplifier.net/forschungsnetzcovid-19/observation-example-body-height

        KorpergrosseComposition result = new KorpergrosseComposition();
        GrosseLangeObservation grosseLangeObservation = new GrosseLangeObservation();

        DateTimeType fhirEffectiveDateTime;
        try {
            // default for every observation
            fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

            //BSa Wie erscheint das im Mapping? Und wo finde ich das Referenzmodell mit diesen Infos zu openEHR?
            grosseLangeObservation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            grosseLangeObservation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            grosseLangeObservation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            grosseLangeObservation.setSubject(new PartySelf());

            // special mapping content
            grosseLangeObservation.setGrosseLangeUnits(observation.getValueQuantity().getCode());
            //BSa Hier ist nicht sichtbar, ob Körpergröße oder Geburt gesetzt wird -> woher die Info?
            grosseLangeObservation.setGrosseLangeMagnitude(observation.getValueQuantity().getValue().doubleValue());

        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setGrosseLange(grosseLangeObservation);

        // Required fields by API
        //BSa Wird hier noch was automatisiert?
        result.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        result.setLocation("test");
        result.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        result.setComposer(new PartySelf()); // FIXME: id ausdenken oder weglassen?

        return result;
    }
}
