package org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode implements EnumValueSet {
  AMENDED("amended", "", "local", "at0009"),

  FINAL("final", "", "local", "at0008"),

  REGISTERED("registered", "", "local", "at0006"),

  PRELIMINARY("preliminary", "", "local", "at0007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode(String value, String description, String terminologyId, String code) {
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
