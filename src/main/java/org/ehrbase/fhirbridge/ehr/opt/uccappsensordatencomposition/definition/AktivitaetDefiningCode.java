package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AktivitaetDefiningCode implements EnumValueSet {
  GEHEN("Gehen", "Die Aktivität war Fortbewegung zu Fuß, langsamer als Laufen/Joggen.", "local", "at0035"),

  WANDERN("Wandern", "Aufzeichung von Aktivitäten bei Fortbewegung zu Fuß im Gelände.", "local", "at0042"),

  RUHE("Ruhe", "Körper ruht, keine Bewegung wird gemessen.", "local", "at0055"),

  LAUFEN("Laufen", "Die Aktivität war Fortbewegung zu Fuß, in einem sportlichen Jogging- /Lauftempo.", "local", "at0036");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AktivitaetDefiningCode(String value, String description, String terminologyId, String code) {
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
