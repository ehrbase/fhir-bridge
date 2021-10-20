package org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LabortestBezeichnungDefiningCode implements EnumValueSet {
  GAS_PANEL_BLOOD("Gas panel - Blood", "", "http://loinc.org", "24338-6"),

  GAS_PANEL_CAPILLARY_BLOOD("Gas panel - Capillary blood", "", "http://loinc.org", "24337-8"),

  GAS_PANEL_ARTERIAL_BLOOD("Gas panel - Arterial blood", "", "http://loinc.org", "24336-0");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LabortestBezeichnungDefiningCode(String value, String description, String terminologyId,
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
