package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;


public enum AntiBodyProfileUrl {
    AB_PRESENCE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-ab-ser-pl-ql-ia"),
    AB_UNITS_VOLUME("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-ab-ser-pl-ia-acnc"),
    IGA_AB_PRESENCE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-iga-ser-pl-ql-ia"),
    IGA_AB_UNITS_VOLUME("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-iga-ser-pl-ia-acnc"),
    IGM_AB_PRESENCE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-igm-ser-pl-ql-ia"),
    IGM_AB_UNITS_VOLUME("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-igm-ser-pl-ia-acnc"),
    IGG_AB_PRESENCE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-igg-ser-pl-ql-ia"),
    IGG_AB_UNITS_VOLUME("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sars-cov-2-igg-ser-pl-ia-acnc");

    private final String url;

    AntiBodyProfileUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
