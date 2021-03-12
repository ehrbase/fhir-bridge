package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KontakttypDefiningCode implements EnumValueSet {
  TELEFON("Telefon", "Angaben zur Telefonnummer.", "local", "at0013"),

  PAGER("Pager", "Angaben zur Pager-Nummer.", "local", "at0016"),

  MOBILTELEFON("Mobiltelefon", "Angaben zur Mobiltelefonnummer.", "local", "at0015"),

  TELEFAX("Telefax", "Angaben zur Faxnummer.", "local", "at0014");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KontakttypDefiningCode(String value, String description, String terminologyId, String code) {
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
