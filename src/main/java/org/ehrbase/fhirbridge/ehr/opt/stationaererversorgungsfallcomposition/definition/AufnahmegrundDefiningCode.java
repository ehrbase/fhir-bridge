package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AufnahmegrundDefiningCode implements EnumValueSet {
  STATIONSAEQUIVALENTE_BEHANDLUNG("Stationsäquivalente Behandlung", "", "§21 KHEntgG", "10"),

  VORSTATIONAERE_BEHANDLUNG_OHNE_ANSCHLIESSENDE_VOLLSTATIONAERE_BEHANDLUNG("vorstationäre Behandlung ohne anschließende vollstationäre Behandlung", "", "§21 KHEntgG", "04"),

  GEBURT("Geburt", "", "§21 KHEntgG", "06"),

  KRANKENHAUSBEHANDLUNG_VOLLSTATIONAER("Krankenhausbehandlung, vollstationär", "", "§21 KHEntgG", "01"),

  KRANKENHAUSBEHANDLUNG_VOLLSTATIONAER_MIT_VORAUSGEGANGENER_VORSTATIONAERER_BEHANDLUNG("Krankenhausbehandlung, vollstationär mit vorausgegangener vorstationärer Behandlung", "", "§21 KHEntgG", "02"),

  KRANKENHAUSBEHANDLUNG_TEILSTATIONAER("Krankenhausbehandlung, teilstationär", "", "§21 KHEntgG", "03"),

  STATIONAERE_AUFNAHME_ZUR_ORGANENTNAHME("Stationäre Aufnahme zur Organentnahme", "", "§21 KHEntgG", "08"),

  STATIONAERE_ENTBINDUNG("Stationäre Entbindung", "", "§21 KHEntgG", "05"),

  WIEDERAUFNAHME_WEGEN_KOMPLIKATIONEN_FALLPAUSCHALE_NACH_KFPV2003("Wiederaufnahme wegen Komplikationen (Fallpauschale) nach KFPV 2003", "", "§21 KHEntgG", "07");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AufnahmegrundDefiningCode(String value, String description, String terminologyId, String code) {
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
