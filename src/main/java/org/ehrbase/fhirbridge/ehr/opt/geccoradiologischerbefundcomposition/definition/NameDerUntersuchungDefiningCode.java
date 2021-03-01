package org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDerUntersuchungDefiningCode implements EnumValueSet {
  DIAGNOSTIC_IMAGING_STUDY("Diagnostic imaging study", "Diagnostic imaging study", "LOINC", "18748-4");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDerUntersuchungDefiningCode(String value, String description, String terminologyId,
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
