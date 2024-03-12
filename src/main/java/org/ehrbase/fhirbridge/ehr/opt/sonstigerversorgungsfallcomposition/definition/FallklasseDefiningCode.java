package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum FallklasseDefiningCode implements EnumValueSet {
  AMBULANT_AMBULATORY("Ambulant (ambulatory)", "", "local_terms", "AMB"),

  PRAESTATIONAER_PRE_ADMISSION("Prästationär (pre-admission)", "", "local_terms", "PRENC"),

  VIRTUELL_VIRTUAL("Virtuell (virtual)", "", "local_terms", "VR"),

  STATIONSAEQUIVALENT_HOME_HEALTH("Stationsäquivalent (home health)", "", "local_terms", "HH");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  FallklasseDefiningCode(String value, String description, String terminologyId, String code) {
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
