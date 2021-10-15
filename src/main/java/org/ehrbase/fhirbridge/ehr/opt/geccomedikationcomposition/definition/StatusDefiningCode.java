package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode implements EnumValueSet {
  BEABSICHTIGT("Beabsichtigt", "Die Medikation kann irgendwann in der Zukunft angewendet werden.", "local", "at0005"),

  UNBEKANNT("Unbekannt", "Der Status des Medikaments ist derzeit nicht bekannt.", "local", "at0008"),

  AKTIV("Aktiv", "Das Medikament wird noch angewendet.", "local", "at0002"),

  FEHLERHAFTE_ANWENDUNG("Fehlerhafte Anwendung", "Einige der Aktionen, die im Medikation Statement enthalten sind, können bereits stattgefunden haben. Beispielsweise kann der Patient einen Teil der Medikamente eingenommen haben.", "local", "at0004"),

  ANGEHALTEN("Angehalten", "Die im Medikation Statement implizierten Aktionen wurden vorübergehend gestoppt, werden aber voraussichtlich später fortgesetzt. Kann auch als \"ausgesetzt\" bezeichnet werden.", "local", "at0007"),

  ABGESCHLOSSEN("Abgeschlossen", "Das Medikament wird nicht mehr angewendet.", "local", "at0003"),

  GESTOPPT("Gestoppt", "Die im Medikation Statement implizierten Aktionen wurden dauerhaft gestoppt, bevor alle von ihnen eingetreten sind. Dies sollte nicht verwendet werden, wenn Medikation Statement versehentlich eingegeben wurde.", "local", "at0006"),

  NICHT_GENOMMEN("Nicht genommen", "Das Medikament wurde vom Patienten nicht angewendet.", "local", "at0009");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode(String value, String description, String terminologyId, String code) {
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
