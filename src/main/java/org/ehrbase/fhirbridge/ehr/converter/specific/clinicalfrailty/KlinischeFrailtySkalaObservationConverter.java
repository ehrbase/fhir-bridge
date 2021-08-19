package org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.Observation;

public class KlinischeFrailtySkalaObservationConverter extends ObservationToObservationConverter<KlinischeFrailtySkalaCfsObservation> {

    @Override
    protected KlinischeFrailtySkalaCfsObservation convertInternal(Observation resource) {
        KlinischeFrailtySkalaCfsObservation klinischeFrailtySkalaCfsObservation = new KlinischeFrailtySkalaCfsObservation();
        if (resource.hasValueCodeableConcept() && resource.getValueCodeableConcept().hasCoding() && resource.getValueCodeableConcept().getCoding().get(0).hasCode()) {
            DvOrdinal ordAssessment = convertFrailtyBeurteilung(Integer.parseInt(resource.getValueCodeableConcept().getCoding().get(0).getCode()));
            klinischeFrailtySkalaCfsObservation.setBeurteilung(ordAssessment);
        }
        return klinischeFrailtySkalaCfsObservation;
    }

    private DvOrdinal convertFrailtyBeurteilung(int code) {
        switch (code) {
            case 1:
                return ClinicalFrailtyBeurteilung.SEHR_FIT.getBerurteilung();
            case 2:
                return ClinicalFrailtyBeurteilung.DURCHSCHNITTLICH_AKTIV.getBerurteilung();
            case 3:
                return ClinicalFrailtyBeurteilung.GUT_ZURECHTKOMMEND.getBerurteilung();
            case 4:
                return ClinicalFrailtyBeurteilung.VULNERABEL.getBerurteilung();
            case 5:
                return ClinicalFrailtyBeurteilung.GERINGGRADIG_FRAIL.getBerurteilung();
            case 6:
                return ClinicalFrailtyBeurteilung.MITTELGRADIG_FRAIL.getBerurteilung();
            case 7:
                return ClinicalFrailtyBeurteilung.AUSGEPRAEGT_FRAIL.getBerurteilung();
            case 8:
                return ClinicalFrailtyBeurteilung.EXTREM_FRAIL.getBerurteilung();
            case 9:
                return ClinicalFrailtyBeurteilung.TERMINAL_ERKRANKT.getBerurteilung();
            default:
                throw new ConversionException("Cannot match beurteilung\"" + code + "\"");
        }
    }
}
