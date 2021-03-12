package org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class ClinicalFrailtyScaleScoreCompositionConverter extends AbstractCompositionConverter<Observation, KlinischeFrailtySkalaComposition> {

    @Override
    public KlinischeFrailtySkalaComposition convert(@NonNull Observation observation) {
        KlinischeFrailtySkalaComposition result = new KlinischeFrailtySkalaComposition();
        mapCommonAttributes(observation, result);
        KlinischeFrailtySkalaCfsObservation klinischeFrailtySkalaCfsObservation = new KlinischeFrailtySkalaCfsObservation();

        DateTimeType fhirEffectiveDateTime = null;
        try {
            fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
            klinischeFrailtySkalaCfsObservation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            klinischeFrailtySkalaCfsObservation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            klinischeFrailtySkalaCfsObservation.setLanguage(Language.DE);
            klinischeFrailtySkalaCfsObservation.setSubject(new PartySelf());
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
        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return result;
    }

}

