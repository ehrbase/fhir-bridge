package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AussageUeberDenAusschlussDefiningCode implements EnumValueSet {
  KNOWN_ABSENT_QUALIFIER_VALUE("Known absent (qualifier value)", "", "http://snomed.info/sct", "410594000");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AussageUeberDenAusschlussDefiningCode(String value, String description, String terminologyId,
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
