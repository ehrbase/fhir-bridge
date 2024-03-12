package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VerwendungDefiningCode implements EnumValueSet {
  GESCHAEFTLICH("Geschäftlich", "Adresse des physischen Standorts eines Unternehmens oder Büros.", "local", "at0015"),

  VORRUEBERGEHENDE_UNTERKUNFT("Vorrübergehende Unterkunft", "Adresse, an der eine Person vorübergehend lebt.", "local", "at0017"),

  PRIVAT("Privat", "Adresse, an der eine Person regelmäßig lebt.", "local", "at0016");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VerwendungDefiningCode(String value, String description, String terminologyId, String code) {
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
