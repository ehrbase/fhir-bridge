package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ImmunsstatusDefiningCode implements EnumValueSet {
  IMPFSTATUS_IST_NICHT_AKUTELL("Impfstatus ist nicht akutell", "Der Impfstatus ist nicht auf dem akutellen Stand.", "local", "at0012"),

  IMPFSTATUS_IST_AKUTELL("Impfstatus ist akutell", "Der Impfstatus ist auf dem akutellen Stand.", "local", "at0011");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ImmunsstatusDefiningCode(String value, String description, String terminologyId, String code) {
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
