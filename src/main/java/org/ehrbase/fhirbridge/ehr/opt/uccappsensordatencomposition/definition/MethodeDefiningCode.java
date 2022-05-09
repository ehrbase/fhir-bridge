package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MethodeDefiningCode implements EnumValueSet {
  AUTOMATISCH_NICHT_INVASIV("Automatisch, nicht-invasiv", "Die Untersuchungsergebnisse werden nicht-invasiv unter Anwendung eines Gerätes, wie zum Beispiel mit Hilfe eines Pulsoximeters oder eines Stethoskops, gemessen.", "local", "at1034");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MethodeDefiningCode(String value, String description, String terminologyId, String code) {
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
