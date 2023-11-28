package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LabortestKategorieDefiningCode implements EnumValueSet {
  LABORATORY("laboratory", "", "LOINC", "26436-6");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LabortestKategorieDefiningCode(String value, String description, String terminologyId,
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
