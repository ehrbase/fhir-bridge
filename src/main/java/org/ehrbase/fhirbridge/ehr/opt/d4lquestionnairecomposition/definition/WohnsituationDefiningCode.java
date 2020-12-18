package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum WohnsituationDefiningCode implements EnumValueSet {
  ALLEIN_WOHNEND("Allein wohnend", "Allein wohnend", "LOINC", "LA6255-9"),

  WOHNT_MIT_ANDEREN_ZUSAMMEN("Wohnt mit anderen zusammen", "Wohnt mit anderen zusammen", "LOINC", "LA9996-5");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  WohnsituationDefiningCode(String value, String description, String terminologyId, String code) {
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
