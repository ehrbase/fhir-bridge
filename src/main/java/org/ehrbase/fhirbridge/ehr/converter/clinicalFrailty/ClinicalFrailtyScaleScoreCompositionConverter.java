package org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class ClinicalFrailtyScaleScoreCompositionConverter extends CompositionConverter<Observation, KlinischeFrailtySkalaComposition> {

    @Override
    protected KlinischeFrailtySkalaComposition convertInternal(Observation resource) {
        KlinischeFrailtySkalaComposition composition = new KlinischeFrailtySkalaComposition();
        KlinischeFrailtySkalaCfsObservation observation = new KlinischeFrailtySkalaCfsObservation();

        DateTimeType fhirEffectiveDateTime;
        try {
            fhirEffectiveDateTime = resource.getEffectiveDateTimeType();
            observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            observation.setLanguage(Language.DE);
            observation.setSubject(new PartySelf());
            int assessment = Integer.parseInt(resource.getValueCodeableConcept().getCoding().get(0).getCode());
            ClinicalFrailtyMappingAssessment mapping = new ClinicalFrailtyMappingAssessment();
            observation.setBeurteilung(mapping.getDVOrdinal(assessment));
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        composition.setKlinischeFrailtySkalaCfs(observation);

        // Required fields by API
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }
}

