package org.ehrbase.fhirbridge.ehr.converter.specificconverters.clinicalFrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;

public class ClinicalFrailtyMappingAssessment {
    private static final String local = "local";


    public DvOrdinal getDVOrdinal(int code) {
        DvOrdinal ret;
        switch (code) {
            case 1:
                ret = ClinicalFrailtyBerurteilung.SEHR_FIT.getBerurteilung();
                break;
            case 2:
                ret = ClinicalFrailtyBerurteilung.DURCHSCHNITTLICH_AKTIV.getBerurteilung();
                break;
            case 3:
                ret = ClinicalFrailtyBerurteilung.GUT_ZURECHTKOMMEND.getBerurteilung();
                break;
            case 4:
                ret = ClinicalFrailtyBerurteilung.VULNERABEL.getBerurteilung();
                break;
            case 5:
                ret = ClinicalFrailtyBerurteilung.GERINGGRADIG_FRAIL.getBerurteilung();
                break;
            case 6:
                ret = ClinicalFrailtyBerurteilung.MITTELGRADIG_FRAIL.getBerurteilung();
                break;
            case 7:
                ret = ClinicalFrailtyBerurteilung.AUSGEPRAEGT_FRAIL.getBerurteilung();
                break;
            case 8:
                ret = ClinicalFrailtyBerurteilung.EXTREM_FRAIL.getBerurteilung();
                break;
            case 9:
                ret = ClinicalFrailtyBerurteilung.TERMINAL_ERKRANKT.getBerurteilung();
                break;
            default:
                throw new UnprocessableEntityException("Cannot match beurteilung\"" + code + "\"");
        }
        return ret;
    }
}
