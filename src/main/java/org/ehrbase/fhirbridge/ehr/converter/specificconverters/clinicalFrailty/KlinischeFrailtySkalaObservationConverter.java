package org.ehrbase.fhirbridge.ehr.converter.specificconverters.clinicalFrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

public class KlinischeFrailtySkalaObservationConverter extends ObservationToObservationConverter<KlinischeFrailtySkalaCfsObservation> {

    @Override
    protected KlinischeFrailtySkalaCfsObservation convertInternal(Observation resource) {
        KlinischeFrailtySkalaCfsObservation klinischeFrailtySkalaCfsObservation = new KlinischeFrailtySkalaCfsObservation();
        DateTimeType fhirEffectiveDateTime = null;
        try {
            //TODO refactor
            fhirEffectiveDateTime = resource.getEffectiveDateTimeType();
            klinischeFrailtySkalaCfsObservation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            klinischeFrailtySkalaCfsObservation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            klinischeFrailtySkalaCfsObservation.setLanguage(Language.DE);
            String string_assessment = resource.getValueCodeableConcept().getCoding().get(0).getCode();
            int assessment = Integer.parseInt(string_assessment);
            org.ehrbase.fhirbridge.ehr.converter.specificconverters.clinicalFrailty.ClinicalFrailtyMappingAssessment mapping = new org.ehrbase.fhirbridge.ehr.converter.specificconverters.clinicalFrailty.ClinicalFrailtyMappingAssessment();
            DvOrdinal ord_assessment = mapping.getDVOrdinal(assessment);
            klinischeFrailtySkalaCfsObservation.setBeurteilung(ord_assessment);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        return klinischeFrailtySkalaCfsObservation;    }

}
