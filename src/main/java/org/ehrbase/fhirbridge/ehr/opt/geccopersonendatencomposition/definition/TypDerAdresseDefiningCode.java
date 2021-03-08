package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TypDerAdresseDefiningCode implements EnumValueSet {
  POSTADRESSEN("Postadressen", "Postalischer Typ der Anschrift.", "local", "at0007"),

  PHYSISCH("Physisch", "Physischen Typ der Anschrift.", "local", "at0008"),

  BEIDES("Beides", "Adresse, die sowohl physisch als auch postalisch ist.", "local", "at0009");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  TypDerAdresseDefiningCode(String value, String description, String terminologyId, String code) {
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
