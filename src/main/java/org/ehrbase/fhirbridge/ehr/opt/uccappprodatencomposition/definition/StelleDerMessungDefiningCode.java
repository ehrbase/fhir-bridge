package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StelleDerMessungDefiningCode implements EnumValueSet {
  LINKER_OBERSCHENKEL("Linker Oberschenkel", "Der linke Oberschenkel der Person.", "local", "at0028"),

  RECHTES_FUSSGELENK("Rechtes Fußgelenk", "Das rechte Fußgelenk der Person.", "local", "at1026"),

  FUSSRUECKEN("Fußrücken", "Fußrücken des Individuums.", "local", "at1056"),

  INTRAARTERIELL("Intraarteriell", "Invasive Messung mittels Transducer über einen arteriellen Zugang.", "local", "at1053"),

  LINKES_FUSSGELENK("Linkes Fußgelenk", "Das linke Fußgelenk der Person", "local", "at1031"),

  RECHTES_HANDGELENK("Rechtes Handgelenk", "Das rechte Handgelenk der Person.", "local", "at1020"),

  LINKES_HANDGELENK("Linkes Handgelenk", "Das linke Handgelenk der Person.", "local", "at1021"),

  ZEH("Zeh", "Ein Zeh des Individuums.", "local", "at1051"),

  RECHTER_ARM("Rechter Arm", "Der rechte Arm der Person.", "local", "at0025"),

  RECHTER_OBERSCHENKEL("Rechter Oberschenkel", "Der rechte Oberschenkel der Person.", "local", "at0027"),

  FINGER("Finger", "Ein Finger des Individuums.", "local", "at1032"),

  LINKER_ARM("Linker Arm", "Der linke Arm der Person.", "local", "at0026");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StelleDerMessungDefiningCode(String value, String description, String terminologyId,
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
