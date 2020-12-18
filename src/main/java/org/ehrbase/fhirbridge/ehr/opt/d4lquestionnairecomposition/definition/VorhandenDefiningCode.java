package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VorhandenDefiningCode implements EnumValueSet {
  ICH_WEISS_ES_NICHT("Ich weiß es nicht", "Ich weiß es nicht", "LOINC", "LA12688-0"),

  NEIN("Nein", "Nein", "LOINC", "LA32-8"),

  JA("Ja", "Ja", "LOINC", "LA33-6");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VorhandenDefiningCode(String value, String description, String terminologyId, String code) {
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
