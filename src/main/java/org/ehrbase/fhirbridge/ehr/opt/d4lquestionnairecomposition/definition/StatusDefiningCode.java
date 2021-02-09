package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode implements EnumValueSet {
  SCHWANGER("Schwanger", "Die Person ist schwanger.", "LOINC", "at0012"),

  MOEGLICHE_SCHWANGERSCHAFT("Mögliche Schwangerschaft", "Die Person glaubt, dass sie schwanger sein könnte.", "LOINC", "at0024"),

  NICHT_SCHWANGER("nicht schwanger", "nicht schwanger", "LOINC", "LA26683-5"),

  UNBEKANNT("Unbekannt", "Unbekannt", "LOINC", "LA4489-6");

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
