package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RegisternameDefiningCode implements EnumValueSet {
  NCT_NUMBER("NCT number", "", "eCRF", "05"),

  EUDRACT_NUMBER("EudraCT Number", "", "eCRF", "04");
  //EUDRACT_NUMBER("EudraCT Number‎", "", "eCRF", "04");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RegisternameDefiningCode(String value, String description, String terminologyId, String code) {
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
