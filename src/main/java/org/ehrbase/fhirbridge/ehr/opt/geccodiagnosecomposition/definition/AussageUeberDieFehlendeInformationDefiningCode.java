package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AussageUeberDieFehlendeInformationDefiningCode implements EnumValueSet {
  UNKNOWN_QUALIFIER_VALUE("Unknown (qualifier value)", "", "http://snomed.info/sct", "Unknown (qualifier value)");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AussageUeberDieFehlendeInformationDefiningCode(String value, String description,
      String terminologyId, String code) {
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
