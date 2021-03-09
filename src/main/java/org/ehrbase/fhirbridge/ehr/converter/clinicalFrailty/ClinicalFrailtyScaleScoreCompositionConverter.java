package org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class ClinicalFrailtyScaleScoreCompositionConverter implements CompositionConverter<KlinischeFrailtySkalaComposition, Observation> {

    @Override
    public Observation fromComposition(KlinischeFrailtySkalaComposition composition) {
        return null;
    }

    @Override
    public KlinischeFrailtySkalaComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        KlinischeFrailtySkalaComposition result = new KlinischeFrailtySkalaComposition();
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);
        KlinischeFrailtySkalaCfsObservation klinischeFrailtySkalaCfsObservation = new KlinischeFrailtySkalaCfsObservation();

        DateTimeType fhirEffectiveDateTime = null;
        try {
            fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
            klinischeFrailtySkalaCfsObservation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            klinischeFrailtySkalaCfsObservation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            klinischeFrailtySkalaCfsObservation.setLanguage(Language.DE);
            String string_assessment = observation.getValueCodeableConcept().getCoding().get(0).getCode();
            int assessment = Integer.parseInt(string_assessment);
            ClinicalFrailtyMappingAssessment mapping = new ClinicalFrailtyMappingAssessment();
            DvOrdinal ord_assessment = mapping.getDVOrdinal(assessment);
            klinischeFrailtySkalaCfsObservation.setBeurteilung(ord_assessment);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setKlinischeFrailtySkalaCfs(klinischeFrailtySkalaCfsObservation);

        // Required fields by API
        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        result.setComposer(new PartySelf());

        return result;
    }

}

