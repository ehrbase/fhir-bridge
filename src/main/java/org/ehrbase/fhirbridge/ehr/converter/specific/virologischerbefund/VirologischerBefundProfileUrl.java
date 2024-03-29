package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

public enum VirologischerBefundProfileUrl {

    DIAGNOSTIC_REPORT("http://highmed.org/fhir/StructureDefinition/ic/DiagnosticReportLab"),
    SPECIMEN("http://hl7.org/fhir/StructureDefinition/Specimen");

    private final String url;

    VirologischerBefundProfileUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


}
