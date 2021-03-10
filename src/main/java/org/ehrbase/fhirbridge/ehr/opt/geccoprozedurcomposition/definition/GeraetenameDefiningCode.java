package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum GeraetenameDefiningCode implements EnumValueSet {
  HIGH_FLOW_OXYGEN_NASAL_CANNULA_PHYSICAL_OBJECT("High flow oxygen nasal cannula (physical object)", "", "SNOMED Clinical Terms", "426854004"),

  TRACHEOSTOMY_TUBE_DEVICE_PHYSICAL_OBJECT("Tracheostomy tube, device (physical object)", "", "SNOMED Clinical Terms", "129121000"),

  ENDOTRACHEAL_TUBE_DEVICE_PHYSICAL_OBJECT("Endotracheal tube, device (physical object)", "", "SNOMED Clinical Terms", "26412008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  GeraetenameDefiningCode(String value, String description, String terminologyId, String code) {
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
