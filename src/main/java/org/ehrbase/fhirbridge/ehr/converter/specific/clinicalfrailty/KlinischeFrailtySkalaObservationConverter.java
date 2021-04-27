package org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.Observation;

public class KlinischeFrailtySkalaObservationConverter extends ObservationToObservationConverter<KlinischeFrailtySkalaCfsObservation> {

    @Override
    protected KlinischeFrailtySkalaCfsObservation convertInternal(Observation resource) {
        KlinischeFrailtySkalaCfsObservation klinischeFrailtySkalaCfsObservation = new KlinischeFrailtySkalaCfsObservation();
        try {
            String stringAssessment = resource.getValueCodeableConcept().getCoding().get(0).getCode();
            int assessment = Integer.parseInt(stringAssessment);
            org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty.ClinicalFrailtyMappingAssessment mapping = new org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty.ClinicalFrailtyMappingAssessment();
            DvOrdinal ordAssessment = mapping.getDVOrdinal(assessment);
            klinischeFrailtySkalaCfsObservation.setBeurteilung(ordAssessment);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        return klinischeFrailtySkalaCfsObservation;
    }

}
