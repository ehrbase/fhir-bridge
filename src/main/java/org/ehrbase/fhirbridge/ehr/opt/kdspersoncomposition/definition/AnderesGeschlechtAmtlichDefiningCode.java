package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AnderesGeschlechtAmtlichDefiningCode implements EnumValueSet {
  DIVERS("divers", "", "http://fhir.de/ValueSet/gender-other-de", "D"),

  UNBESTIMMT("unbestimmt", "", "http://fhir.de/ValueSet/gender-other-de", "X");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AnderesGeschlechtAmtlichDefiningCode(String value, String description, String terminologyId,
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
