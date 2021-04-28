package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode2 implements EnumValueSet {
  AKTIV("Aktiv", "Das Medikament wird derzeit angewendet.", "local", "at0011"),

  UNBEKANNT("Unbekannt", "Der Status der Medikamentenanwendung ist nicht bekannt.", "local", "at0017"),

  VORGESEHEN("Vorgesehen", "Das Medikament soll in Zukunft zu einem nicht festgelegten Zeitpunkt angewendet werden.", "local", "at0009"),

  GEPLANT("Geplant", "Das Medikament soll zu einem späteren Zeitpunkt eingenommen werden.", "local", "at0010"),

  ABGEBROCHEN("Abgebrochen", "Die Medikamentenanwendung wurde begonnen und dann eingestellt.", "local", "at0016"),

  ABGESCHLOSSEN("Abgeschlossen", "Das Medikament wird nicht mehr angewendet.", "local", "at0012"),

  VERSCHOBEN("Verschoben", "Die Anwendung des Medikaments ist geplant, der Beginn wurde jedoch vorübergehend verzögert.", "local", "at0013"),

  UNTERBROCHEN("Unterbrochen", "Die Medikamentenanwendung wurde begonnen, aber vorübergehend ausgesetzt.", "local", "at0015"),

  WIDERRUFEN("Widerrufen", "Das Medikament sollte angewendet werden, wurde jedoch vor Beginn abgesagt.", "local", "at0014");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode2(String value, String description, String terminologyId, String code) {
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
