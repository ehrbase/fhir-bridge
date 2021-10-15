package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDefiningCode implements EnumValueSet {
  POSTALISCH("Postalisch", "Adresse, die als Ziel für den Versand von Briefen oder Paketen verwendet wird.", "local", "at0003"),

  PHYSISCH("Physisch", "Ein physischer Ort, der besucht werden kann.", "local", "at0002"),

  BEIDES("Beides", "Adresse eines physischen Standorts, der auch als Ziel für E-Mails verwendet wird.", "local", "at0004");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDefiningCode(String value, String description, String terminologyId, String code) {
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
