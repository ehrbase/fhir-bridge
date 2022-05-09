package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RegelmaessigkeitDefiningCode implements EnumValueSet {
  REGELMAESSIG("Regelmäßig", "Der Verlauf ist regelmäßig.", "local", "at0006"),

  UNREGELMAESSIG("Unregelmäßig", "Der Verlauf ist unregelmäßig.", "local", "at1028");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RegelmaessigkeitDefiningCode(String value, String description, String terminologyId,
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
