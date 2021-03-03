package org.ehrbase.fhirbridge.ehr.converter.convertercodes;

public enum CodeSystem {

    LOINC("http://loinc.org"),

    SNOMED("http://snomed.info/sct");

    private final String url;

    CodeSystem(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

}
