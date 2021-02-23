package org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class ClinicalFrailtyScaleScoreCompositionConverter implements CompositionConverter<KlinischeFrailtySkalaComposition, Observation> {

    @Override
    public Observation fromComposition(KlinischeFrailtySkalaComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public KlinischeFrailtySkalaComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        KlinischeFrailtySkalaComposition result = new KlinischeFrailtySkalaComposition();
        KlinischeFrailtySkalaCfsObservation klinischeFrailtySkalaCfsObservation = new KlinischeFrailtySkalaCfsObservation();

        DateTimeType fhirEffectiveDateTime = null;
        try {

            // default for every observation
            fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
            klinischeFrailtySkalaCfsObservation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            klinischeFrailtySkalaCfsObservation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            klinischeFrailtySkalaCfsObservation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            klinischeFrailtySkalaCfsObservation.setSubject(new PartySelf());

            // special mapping content

            // is this the correct equivalent?
            //Fhir-Example: Observation.valueCodeableConcept[0].coding[0].code[0]
            // I tried to access the object, but it crashes during runtime:
            String string_assessment = observation.getValueCodeableConcept().getCoding().get(0).getCode();
            int assessment = Integer.parseInt(string_assessment);

            // get the mapping to the DV_Ordinal from inner class
            ClinicalFrailtyMappingAssessment mapping = new ClinicalFrailtyMappingAssessment();
            DvOrdinal ord_assessment = mapping.getDVOrdinal(assessment);

            klinischeFrailtySkalaCfsObservation.setBeurteilung(ord_assessment);

            //observation.setMesswertMagnitude(fhirValueNumeric.doubleValue());
            //observation.setMesswertUnits(fhirValue.getUnit());


        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setKlinischeFrailtySkalaCfs(klinischeFrailtySkalaCfsObservation);

        // Required fields by API
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

