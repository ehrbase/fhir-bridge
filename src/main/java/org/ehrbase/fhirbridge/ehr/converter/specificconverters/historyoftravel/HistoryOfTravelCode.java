package org.ehrbase.fhirbridge.ehr.converter.specificconverters.historyoftravel;

import static org.ehrbase.fhirbridge.ehr.converter.specificconverters.convertercodes.CodeSystem.LOINC;

public enum HistoryOfTravelCode {

    LOINC_DATE_TRAVEL_STARTED(LOINC.getUrl(), "82752-7"),
    LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION(LOINC.getUrl(), "91560-3"),
    LOINC_CITY_OF_TRAVEL(LOINC.getUrl(), "94653-3"),
    LOINC_STATE_OF_TRAVEL(LOINC.getUrl(), "82754-3"),
    LOINC_COUNTRY_OF_TRAVEL(LOINC.getUrl(), "94651-7");

    private final String system;
    private final String code;

    HistoryOfTravelCode(String system, String code) {
        this.code = code;
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public String getSystem() {
        return system;
    }
}
