package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum FallstatusDefiningCode implements EnumValueSet {
  FINISHED("Finished", "", "http://hl7.org/fhir/encounter-status", "finished"),

  IN_PROGRESS("In Progress", "", "http://hl7.org/fhir/encounter-status", "in-progress"),

  UNKNOWN("Unknown", "", "http://hl7.org/fhir/encounter-status", "unknown"),

  PLANNED("Planned", "", "http://hl7.org/fhir/encounter-status", "planned"),

  ON_LEAVE("On Leave", "", "http://hl7.org/fhir/encounter-status", "onleave"),

  CANCELLED("Cancelled", "", "http://hl7.org/fhir/encounter-status", "cancelled"),

  ENTERED_IN_ERROR("Entered in Error", "", "http://hl7.org/fhir/encounter-status", "entered-in-error");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  FallstatusDefiningCode(String value, String description, String terminologyId, String code) {
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
