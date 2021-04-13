package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum GeschlechtBeiDerGeburtDefiningCode implements EnumValueSet {
  UNKNOWN("Unknown", "", "http://hl7.org/fhir/administrative-gender", "unknown"),

  DIVERS("Divers", "", "http://hl7.org/fhir/administrative-gender", "D"),

  MALE("Male", "", "http://hl7.org/fhir/administrative-gender", "male"),

  FEMALE("Female", "", "http://hl7.org/fhir/administrative-gender", "female"),

  UNBESTIMMT("Unbestimmt", "", "http://hl7.org/fhir/administrative-gender", "X");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  GeschlechtBeiDerGeburtDefiningCode(String value, String description, String terminologyId,
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
