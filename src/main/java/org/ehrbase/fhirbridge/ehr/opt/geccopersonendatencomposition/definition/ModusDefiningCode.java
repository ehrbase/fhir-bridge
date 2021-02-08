package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ModusDefiningCode implements EnumValueSet {
  ARBEIT("Arbeit", "Angaben zu den Kommunikationswegen der Arbeit.", "local", "at0012"),

  PRIVAT("Privat", "Angaben zu den privaten Kommunikationswegen.", "local", "at0011");

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
