package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AufnahmeanlassDefiningCode implements EnumValueSet {
  NOTFALL("Notfall", "", "§21 KHEntgG", "N"),

  GEBURT("Geburt", "", "§21 KHEntgG", "G"),

  EINWEISUNG_DURCH_EINEN_ARZT("Einweisung durch einen Arzt", "", "§21 KHEntgG", "E"),

  VERLEGUNG_MIT_BEHANDLUNGSDAUER_IM_VERLEGENDEN_KRANKENHAUS_BIS_ZU24_STUNDEN("Verlegung mit Behandlungsdauer im verlegenden Krankenhaus bis zu 24 Stunden", "", "§21 KHEntgG", "A"),

  VERLEGUNG_MIT_BEHANDLUNGSDAUER_IM_VERLEGENDEN_KRANKENHAUS_LAENGER_ALS24_STUNDEN("Verlegung mit Behandlungsdauer im verlegenden Krankenhaus länger als 24 Stunden", "", "§21 KHEntgG", "V"),

  EINWEISUNG_DURCH_EINEN_ZAHNARZT("Einweisung durch einen Zahnarzt", "", "§21 KHEntgG", "Z"),

  BEGLEITPERSON_ODER_MITAUFGENOMMENE_PFLEGEKRAFT("Begleitperson oder mitaufgenommene Pflegekraft", "", "§21 KHEntgG", "B"),

  AUFNAHME_NACH_VORAUSGEHENDER_BEHANDLUNG_IN_EINER_REHABILITATIONSEINRICHTUNG("Aufnahme nach vorausgehender Behandlung in einer Rehabilitationseinrichtung", "", "§21 KHEntgG", "R");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AufnahmeanlassDefiningCode(String value, String description, String terminologyId, String code) {
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
