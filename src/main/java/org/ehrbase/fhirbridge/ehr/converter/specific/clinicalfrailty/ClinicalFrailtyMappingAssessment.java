package org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;

public class ClinicalFrailtyMappingAssessment {

    public DvOrdinal getDVOrdinal(int code) {
        DvOrdinal ret;
        switch (code) {
            case 1:
                ret = ClinicalFrailtyBeurteilung.SEHR_FIT.getBerurteilung();
                break;
            case 2:
                ret = ClinicalFrailtyBeurteilung.DURCHSCHNITTLICH_AKTIV.getBerurteilung();
                break;
            case 3:
                ret = ClinicalFrailtyBeurteilung.GUT_ZURECHTKOMMEND.getBerurteilung();
                break;
            case 4:
                ret = ClinicalFrailtyBeurteilung.VULNERABEL.getBerurteilung();
                break;
            case 5:
                ret = ClinicalFrailtyBeurteilung.GERINGGRADIG_FRAIL.getBerurteilung();
                break;
            case 6:
                ret = ClinicalFrailtyBeurteilung.MITTELGRADIG_FRAIL.getBerurteilung();
                break;
            case 7:
                ret = ClinicalFrailtyBeurteilung.AUSGEPRAEGT_FRAIL.getBerurteilung();
                break;
            case 8:
                ret = ClinicalFrailtyBeurteilung.EXTREM_FRAIL.getBerurteilung();
                break;
            case 9:
                ret = ClinicalFrailtyBeurteilung.TERMINAL_ERKRANKT.getBerurteilung();
                break;
            default:
                throw new UnprocessableEntityException("Cannot match beurteilung\"" + code + "\"");
        }
        return ret;
    }
}
