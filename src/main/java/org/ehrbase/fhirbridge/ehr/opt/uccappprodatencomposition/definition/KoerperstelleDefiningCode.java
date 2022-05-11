package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KoerperstelleDefiningCode implements EnumValueSet {
  OHRLAEPPCHEN("Ohrläppchen", "Das Ohrläppchen eines unbestimmten Ohres.", "local", "at1051"),

  ARTERIA_BRACHIALIS_RECHTS("Arteria brachialis - rechts", "Die rechte Arteria brachialis.", "local", "at1049"),

  ZEH("Zeh", "Ein nicht näher beschriebener Zeh.", "local", "at1054"),

  ARTERIA_BRACHIALIS_LINKS("Arteria brachialis - links", "Die linke Arteria brachialis.", "local", "at1048"),

  FINGER("Finger", "Ein nicht näher beschriebener Finger.", "local", "at1047"),

  ARTERIA_FEMORALIS_LINKS("Arteria femoralis - links", "Die linke Arteria femoralis.", "local", "at1043"),

  HERZ("Herz", "Die Region des Herzens.", "local", "at1040"),

  ARTERIA_CAROTIS_LINKS("Arteria carotis - links", "Die linke Arteria carotis.", "local", "at1041"),

  ARTERIA_RADIALIS_RECHTS("Arteria radialis - rechts", "Die rechte Arteria radialis.", "local", "at1039"),

  ARTERIA_CAROTIS_RECHTS("Arteria carotis - rechts", "Die rechte Arteria carotis.", "local", "at1042"),

  ARTERIA_RADIALIS_LINKS("Arteria radialis - links", "Die linke Arteria radialis.", "local", "at1038"),

  ARTERIA_FEMORALIS_RECHTS("Arteria femoralis - rechts", "Die rechts Arteria femoralis.", "local", "at1044");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KoerperstelleDefiningCode(String value, String description, String terminologyId, String code) {
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
