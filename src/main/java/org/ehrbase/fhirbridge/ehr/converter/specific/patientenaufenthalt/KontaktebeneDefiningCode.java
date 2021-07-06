package org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KontaktebeneDefiningCode implements EnumValueSet {

    EINRICHTUNGS_KONTAKT("Einrichtungskontakt", "Beschreibt den Kontakt zur Einrichtung.", "", "einrichtungskontakt"),

    ABTEILUNGS_KONTAKT("Abteilungskontakt", "Beschreibt den Kontakt zur Abteilung.", "", "abteilungskontakt"),

    VERSORGUNGS_STELLEN_KONTAKT("Versorgungsstellenkontakt", "Beschreibt den Kontakt zur Versorgungsstelle.", "", "versorgungsstellenkontakt");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    KontaktebeneDefiningCode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
