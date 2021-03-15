package org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.AbstractEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturObservation;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

public class KlinischeFrailtySkalaObservationConverter extends AbstractEntryEntityConverter<Observation, KlinischeFrailtySkalaCfsObservation> {
    @Override
    public KlinischeFrailtySkalaCfsObservation convert(Observation resource) {
        KlinischeFrailtySkalaCfsObservation klinischeFrailtySkalaCfsObservation = new KlinischeFrailtySkalaCfsObservation();
        mapCommonAttributes(resource, klinischeFrailtySkalaCfsObservation);
        DateTimeType fhirEffectiveDateTime = null;
        try {
            //TODO refactor
            fhirEffectiveDateTime = resource.getEffectiveDateTimeType();
            klinischeFrailtySkalaCfsObservation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            klinischeFrailtySkalaCfsObservation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            klinischeFrailtySkalaCfsObservation.setLanguage(Language.DE);
            String string_assessment = resource.getValueCodeableConcept().getCoding().get(0).getCode();
            int assessment = Integer.parseInt(string_assessment);
            ClinicalFrailtyMappingAssessment mapping = new ClinicalFrailtyMappingAssessment();
            DvOrdinal ord_assessment = mapping.getDVOrdinal(assessment);
            klinischeFrailtySkalaCfsObservation.setBeurteilung(ord_assessment);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        return klinischeFrailtySkalaCfsObservation;
    }
}
