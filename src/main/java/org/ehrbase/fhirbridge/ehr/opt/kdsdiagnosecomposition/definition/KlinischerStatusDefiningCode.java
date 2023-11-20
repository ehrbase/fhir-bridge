package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KlinischerStatusDefiningCode implements EnumValueSet {
  AKTIV("Aktiv", "Das Problem oder die Diagnose ist derzeit aktiv und klinisch relevant.", "local", "at0026"),

  INAKTIV("Inaktiv", "Das Problem oder die Diagnose ist nicht komplett behoben, ist aber inaktiv oder ist weniger relevant in dem gegenwärtigen klinischen Kontext.", "local", "at0027");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KlinischerStatusDefiningCode(String value, String description, String terminologyId,
      String code) {
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
