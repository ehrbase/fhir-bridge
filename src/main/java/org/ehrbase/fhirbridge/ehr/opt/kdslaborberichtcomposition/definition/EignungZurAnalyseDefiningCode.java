package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum EignungZurAnalyseDefiningCode implements EnumValueSet {
  MANGELHAFT_ANALYSIERT("Mangelhaft - analysiert", "Die Probe ist mangelhaft, wurde aber analysiert.", "local", "at0063"),

  MANGELHAFT_NICHT_ANALYSIERT("Mangelhaft - nicht analysiert", "Die Probe ist mangelhaft und wurde nicht analysiert.", "local", "at0064"),

  ZUFRIEDENSTELLEND("Zufriedenstellend", "Die Probe ist von ausreichender Qualität, um eine Berichterstattung zu ermöglichen.", "local", "at0062");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  EignungZurAnalyseDefiningCode(String value, String description, String terminologyId,
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
