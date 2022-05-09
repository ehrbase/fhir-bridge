package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum PhaseDefiningCode implements EnumValueSet {
  IN_RUHE("In Ruhe", "Die Person befindet sich im Ruhezustand vor Beginn der Übung/körperlichen Anstrengung.", "local", "at0006");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  PhaseDefiningCode(String value, String description, String terminologyId, String code) {
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
