package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NamensartDefiningCode implements EnumValueSet {
  BERUFSNAME("Berufsname", "Der Name, den die Person für geschäftliche oder berufliche Zwecke verwendet.", "local", "at0019"),

  FRUEHERER_NAME("Früherer Name", "Von der Person früher benutzter Name.", "local", "at0008"),

  MAEDCHENNAME("Mädchenname", "Der Name der bis zur Heirat benutzt wurde.", "local", "at0012"),

  REGISTRIERTER_NAME("Registrierter Name", "Der Name, unter dem die Person offiziell registriert ist.", "local", "at0020"),

  BERICHTSNAME("Berichtsname", "Der Name der Person, wie er für die Berichterstellung verwendet werden soll, wenn er mit einer bestimmten Kennung verwendet wird.", "local", "at0021"),

  GEBURTSNAME("Geburtsname", "Der Person bei Geburt gegebener Name.", "local", "at0009"),

  ALIAS("Alias", "Ein Anderer von der Person benutzter Name.", "local", "at0011"),

  AKA("AKA", "Die Person ist auch bekannt als.", "local", "at0010");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NamensartDefiningCode(String value, String description, String terminologyId, String code) {
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
