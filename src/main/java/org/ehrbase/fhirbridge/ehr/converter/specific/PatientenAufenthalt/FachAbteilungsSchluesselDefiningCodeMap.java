package org.ehrbase.fhirbridge.ehr.converter.specific.PatientenAufenthalt;

import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.FachabteilungsschluesselDefiningCode;

import java.util.HashMap;
import java.util.Map;

class FachAbteilungsSchluesselDefiningCodeMap {

    private static final Map<String, FachabteilungsschluesselDefiningCode> fachAbteilungsSchluesselMap = new HashMap<>();

    static {

        for(FachabteilungsschluesselDefiningCode fachabteilungsschluesselDefiningCode : FachabteilungsschluesselDefiningCode.values() ) {

            fachAbteilungsSchluesselMap.put(fachabteilungsschluesselDefiningCode.getCode(), fachabteilungsschluesselDefiningCode);
        }
    }

    static Map<String, FachabteilungsschluesselDefiningCode> getFachAbteilungsSchluesselMap() {
        return fachAbteilungsSchluesselMap;
    }
}
