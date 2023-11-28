package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ErgebnisStatusDefiningCode implements EnumValueSet {
  ENDBEFUND("Endbefund", "Das Analyseergebnis ist vollständig und durch eine autorisierte Person verifiziert.", "local", "at0018"),

  REGISTRIERT("Registriert", "Der Test ist im Laborinformationssystem registriert, aber noch kein Ergebnis verfügbar.", "local", "at0015"),

  KORRIGIERT("Korrigiert", "Das Ergebnis wurde nach dem endgültigen Abschluss geändert und ist vollständig und wird von einer autorisierten Person überprüft. Dies ist eine Sub-Kategorie von \"Geändert\".", "local", "at0019"),

  UNVOLLSTAENDIG("Unvollständig", "Das Testergebnis ist ein Anfangs- oder Interimswert: Daten im Testergebnis können unvollständig oder nicht verifiziert/validiert sein.", "local", "at0016"),

  GEAENDERT("Geändert", "Das Ergebnis wurde nach dem endgültigen Abschluss geändert und ist vollständig und von einer autorisierten Person überprüft. Die Ergebnisdaten wurden geändert.", "local", "at0020"),

  ERGAENZT("Ergänzt", "Nach Abschluss wurde der Bericht durch das Hinzufügen neuer Inhalte abgeändert. Die vorhandene Inhalte sind unverändert. Dies ist eine Sub-Kategorie von \"Geändert\".", "local", "at0021"),

  STORNIERT("Storniert", "Das Testergebnis ist nicht verfügbar, weil der Test nicht gestartet oder nicht abgeschlossen wurde (manchmal auch als \"abgebrochen\" bezeichnet).", "local", "at0023"),

  VORLAEUFIG("Vorläufig", "Erste, verifizierte Resultate sind vorhanden, der Test ist aber noch nicht abgeschlossen (Sub-Kategorie von 'Unvollständig').", "local", "at0017"),

  ENDBEFUND_WIDERRUFEN("Endbefund, widerrufen", "Das Testergebnis wurde nach Endbefundung zurückgezogen.", "local", "at0022");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ErgebnisStatusDefiningCode(String value, String description, String terminologyId, String code) {
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
