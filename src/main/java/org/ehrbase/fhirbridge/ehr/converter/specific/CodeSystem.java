package org.ehrbase.fhirbridge.ehr.converter.specific;

public enum CodeSystem {

    LOINC("http://loinc.org"),

    SNOMED("http://snomed.info/sct"),

    KONTAKT_EBENE("https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Kontaktebene"),

    HL7_DATA_ABSENT_REASON("http://terminology.hl7.org/CodeSystem/data-absent-reason"),

    DIMDI_ATC("http://fhir.de/CodeSystem/dimdi/atc"),

    HL7_OBSERVATI0N_CATEGORY("http://terminology.hl7.org/CodeSystem/observation-category");

    private final String url;

    CodeSystem(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

}
