package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDefiningCode2 implements EnumValueSet {
  PAGER("Pager", "*", "local", "at0008"),

  FAX("Fax", "*", "local", "at0009"),

  TELEFON_KEIN_MOBILTELEFON("Telefon (kein Mobiltelefon)", "*", "local", "at0006"),

  MOBILTELEFON("Mobiltelefon", "Für SMS oder Sprachanrufe.", "local", "at0005"),

  E_MAIL("E-Mail", "*", "local", "at0007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDefiningCode2(String value, String description, String terminologyId, String code) {
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
