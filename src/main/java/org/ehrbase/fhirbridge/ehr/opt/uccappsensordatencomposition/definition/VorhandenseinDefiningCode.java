package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VorhandenseinDefiningCode implements EnumValueSet {
  VORHANDEN("Vorhanden", "Eine Puls- oder eine Herzfrequenz kann ermittelt werden.", "local", "at1024"),

  NICHT_ERMITTELBAR("Nicht ermittelbar", "Eine Puls- oder eine Herzfrequenz kann nicht ermittelt werden.", "local", "at1025");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VorhandenseinDefiningCode(String value, String description, String terminologyId, String code) {
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
