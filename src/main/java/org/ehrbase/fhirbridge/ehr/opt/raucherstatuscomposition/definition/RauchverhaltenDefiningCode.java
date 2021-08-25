package org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RauchverhaltenDefiningCode implements EnumValueSet {
  JA("Ja", "", "LOINC", "LA18976-3"),

  EHEMALIGER_RAUCHER("Ehemaliger Raucher", "", "LOINC", "LA15920-4"),

  NICHTRAUCHER("Nichtraucher", "", "LOINC", "LA18978-9"),

  UNBEKANNT("Unbekannt", "", "LOINC", "LA18980-5");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RauchverhaltenDefiningCode(String value, String description, String terminologyId, String code) {
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
