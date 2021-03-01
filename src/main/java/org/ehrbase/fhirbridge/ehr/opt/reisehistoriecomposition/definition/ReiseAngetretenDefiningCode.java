package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ReiseAngetretenDefiningCode implements EnumValueSet {
  YES_QUALIFIER_VALUE("Yes (qualifier value)", "", "SNOMED Clinical Terms", "373066001");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ReiseAngetretenDefiningCode(String value, String description, String terminologyId, String code) {
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
