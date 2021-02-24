package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

public enum BloogGasProfileUrl {
    PH("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH"),
    CARBONDIOXIDE_PARTIAL_PRESSURE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure"),
    OXYGENPARTIAL_PRESSURE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure"),
    OXYGEN_SATURATION("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation");

    private final String url;

    BloogGasProfileUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
