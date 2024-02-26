package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TypDefiningCode implements EnumValueSet {
  HEALTHCARE_PROVIDER("Healthcare Provider", "", "local_terms", "prov"),

  HOSPITAL_DEPARTMENT("Hospital Department", "", "local_terms", "dept");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  TypDefiningCode(String value, String description, String terminologyId, String code) {
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
