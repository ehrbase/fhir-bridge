package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieLoincDefiningCode implements EnumValueSet {
  LABORATORY_STUDIES_SET("Laboratory studies (set)", "", "LOINC", "26436-6");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KategorieLoincDefiningCode(String value, String description, String terminologyId, String code) {
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
