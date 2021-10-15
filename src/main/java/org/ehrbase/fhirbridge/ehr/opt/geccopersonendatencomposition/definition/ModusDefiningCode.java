package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ModusDefiningCode implements EnumValueSet {
  ARBEIT("Arbeit", "Angaben zu den Kommunikationswegen der Arbeit.", "http://hl7.org/fhir/contact-point-use", "at0012"),

  MOBILE("Mobile", "", "http://hl7.org/fhir/contact-point-use", "mobile"),

  PRIVAT("Privat", "Angaben zu den privaten Kommunikationswegen.", "http://hl7.org/fhir/contact-point-use", "at0011"),

  TEMP("Temp", "", "http://hl7.org/fhir/contact-point-use", "temp"),

  OLD("Old", "", "http://hl7.org/fhir/contact-point-use", "old"),

  HOME("Home", "", "http://hl7.org/fhir/contact-point-use", "home"),

  WORK("Work", "", "http://hl7.org/fhir/contact-point-use", "work");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ModusDefiningCode(String value, String description, String terminologyId, String code) {
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
