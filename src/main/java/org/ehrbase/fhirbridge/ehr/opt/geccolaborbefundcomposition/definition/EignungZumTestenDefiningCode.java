package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum EignungZumTestenDefiningCode implements EnumValueSet {
  MANGELHAFT_NICHT_VERARBEITET("Mangelhaft - nicht verarbeitet", "Die Probe ist mangelhaft und wurde nicht verarbeitet.", "local", "at0064"),

  MANGELHAFT_VERARBEITET("Mangelhaft - verarbeitet", "Die Probe ist mangelhaft, wurde aber verarbeitet.", "local", "at0063"),

  ZUFRIEDENSTELLEND("Zufriedenstellend", "Die Probe ist von ausreichender Qualität, um eine Auswertung zu ermöglichen.", "local", "at0062");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  EignungZumTestenDefiningCode(String value, String description, String terminologyId,
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
