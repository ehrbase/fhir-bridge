package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradDefiningCode implements EnumValueSet {
  N38_C("38 °C", "", "local", "38C"),

  N39_C("39 °C", "", "local", "39C"),

  N40_C("40 °C", "", "local", "40C"),

  N41_C("41 °C", "", "local", "41C"),

  N42_C(">42 °C", "", "local", ">42C"),

  ICH_WEISS_ES_NICHT("Ich weiß es nicht", "", "local", "LA12688-0");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SchweregradDefiningCode(String value, String description, String terminologyId, String code) {
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
