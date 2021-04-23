package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;


import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;

public enum AntiBodyPanelCode {
    LABORATORY_STUDIES(CodeSystem.LOINC.getUrl(), "26436-6");

    private final String system;
    private final String code;


    AntiBodyPanelCode( String system, String code) {
        this.code = code;
        this.system = system;
    }

    public String getSystem() {
        return system;
    }

    public String getCode() {
        return code;
    }
}
