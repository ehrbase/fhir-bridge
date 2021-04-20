package org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ErregernameDefiningCode implements EnumValueSet {
  SARS_COV2("SARS-CoV-2", "", "local_terms", "COV");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ErregernameDefiningCode(String value, String description, String terminologyId, String code) {
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
