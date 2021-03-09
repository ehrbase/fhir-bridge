package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CareflowStepDefiningCode implements EnumValueSet {
  PROZEDUR_VERSCHOBEN("Prozedur verschoben", "Die Prozedur wurde verschoben.", "openehr", "at0038"),

  GEPLANTE_PROZEDUR("Geplante Prozedur", "Die Prozedur, die durchgeführt werden soll, ist geplant.", "openehr", "at0004"),

  PROZEDUR_STORNIERT("Prozedur storniert", "Die geplante Prozedur wurde vor Beginn storniert.", "openehr", "at0039"),

  PROZEDUR_BEGONNEN("Prozedur begonnen", "Die Prozedur, oder eine Subprozedur in einem mehrstufigen Vorgehen, wurde begonnen.", "openehr", "at0068"),

  GEPLANTER_TERMIN_DER_PROZEDUR("geplanter Termin der Prozedur", "Ein Termin für die Prozedur wurde geplant.", "openehr", "at0036"),

  AUFTRAG_FUER_PROZEDUR_VERSENDET("Auftrag für Prozedur versendet", "Der Auftrag für die Prozedur wurde versendet.", "openehr", "at0007"),

  PROZEDUR_BEENDET("Prozedur beendet", "Die Prozedur wurde durchgeführt und alle damit verbundenen klinischen Aktivitäten wurden beendet.", "openehr", "at0043"),

  PROZEDUR_DURCHGEFUEHRT("Prozedur durchgeführt", "Die Prozedur, oder eine Subprozedur in einem mehrstufigen Vorgehen, wurde durchgeführt.", "openehr", "at0047"),

  PROZEDUR_UNTERBROCHEN("Prozedur unterbrochen", "Die Prozedur wurde unterbrochen.", "openehr", "at0040"),

  PROZEDUR_ABGEBROCHEN("Prozedur abgebrochen", "Die Prozedur wurde abgebrochen.", "openehr", "at0041");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  CareflowStepDefiningCode(String value, String description, String terminologyId, String code) {
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
