package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KoerperhaltungDefiningCode implements EnumValueSet {
  LIEGEND("Liegend", "Der Patient lag flach.", "local", "at1000"),

  SITZEND("Sitzend", "Der Patient saß (zum Beispiel auf einem Bett oder einem Stuhl).", "local", "at1001"),

  STEHEND_AUFRECHT("Stehend/Aufrecht", "Der Patient stand, ging oder rannte.", "local", "at1003"),

  ZURUECKLEHNEND("Zurücklehnend", "Der Patient saß zurück gelehnt in einem Winkel von 45 Grad und seine Beine waren bis zur Höhe des Beckens angehoben.", "local", "at1002");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KoerperhaltungDefiningCode(String value, String description, String terminologyId, String code) {
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
