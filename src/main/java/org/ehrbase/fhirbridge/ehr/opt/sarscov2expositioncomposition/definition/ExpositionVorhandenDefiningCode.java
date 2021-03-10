package org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ExpositionVorhandenDefiningCode implements EnumValueSet {
  NO_QUALIFIER_VALUE("No (qualifier value)", "", "SNOMED-CT", "373067005"),

  EXPOSURE_TO_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_EVENT("Exposure to severe acute respiratory syndrome coronavirus 2 (event)", "", "SNOMED-CT", "840546002"),

  UNKNOWN("Unknown", "", "SNOMED-CT", "unknown");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ExpositionVorhandenDefiningCode(String value, String description, String terminologyId,
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
