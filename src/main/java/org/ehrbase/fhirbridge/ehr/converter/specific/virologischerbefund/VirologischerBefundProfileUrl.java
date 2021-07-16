package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

public enum VirologischerBefundProfileUrl {

    DIAGNOSTIC_REPORT("https://simplifier.net/medizininformatikinitiative-highmed-ic/highmedicdiagnosticreport"),
    SPECIMEN("https://simplifier.net/medizininformatikinitiative-highmed-ic/specimen-duplicate-5");

    private final String url;

    VirologischerBefundProfileUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


}
