package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KoerperexpositionDefiningCode implements EnumValueSet {
  NACKT("Nackt", "Keine Kleidung, Bettzeug oder andere Bedeckung.", "local", "at0031"),

  ERHOEHTE_KLEIDUNG_BETTZEUG("Erhöhte Kleidung/Bettzeug", "Die Person wird bedeckt von einer größeren Menge an Kleidung oder Bettzeug als für die Umgebungsbedingungen angemessen erscheint.", "local", "at0034"),

  VERMINDERTE_KLEIDUNG_BETTZEUG("Verminderte Kleidung/Bettzeug", "Die Person wird bedeckt von einer geringeren Menge an Kleidung oder Bettzeug als für die Umgebungsbedingungen angemessen erscheint.", "local", "at0032"),

  ANGEMESSENE_KLEIDUNG_BETTZEUG("Angemessene Kleidung/Bettzeug", "Die Person wird bedeckt von einer Menge an Kleidung oder Bettzeug, die den Umgebungsbedingungen angemessen erscheint.", "local", "at0033");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KoerperexpositionDefiningCode(String value, String description, String terminologyId,
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
