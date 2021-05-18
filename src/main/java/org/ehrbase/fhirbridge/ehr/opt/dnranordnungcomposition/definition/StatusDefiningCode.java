package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode implements EnumValueSet {
  VORGESCHLAGEN("vorgeschlagen", "", "http://hl7.org/fhir/consent-state-codes", "vorgeschlagen"),

  VORLAEUFIG("vorläufig", "*", "http://hl7.org/fhir/consent-state-codes", "at0011"),

  FINAL("final", "*", "http://hl7.org/fhir/consent-state-codes", "at0012"),

  EINGABEFEHLER("Eingabefehler", "", "http://hl7.org/fhir/consent-state-codes", "Eingabefehler"),

  REGISTRIERT("registriert", "*", "http://hl7.org/fhir/consent-state-codes", "at0010"),

  VERWORFEN("verworfen", "", "http://hl7.org/fhir/consent-state-codes", "verworfen"),

  GEAENDERT("geändert", "*", "http://hl7.org/fhir/consent-state-codes", "at0013"),

  AKTIV("aktiv", "", "http://hl7.org/fhir/consent-state-codes", "aktiv"),

  INAKTIV("inaktiv", "", "http://hl7.org/fhir/consent-state-codes", "inaktiv"),

  ENTWORFEN("entworfen", "", "http://hl7.org/fhir/consent-state-codes", "entworfen");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
