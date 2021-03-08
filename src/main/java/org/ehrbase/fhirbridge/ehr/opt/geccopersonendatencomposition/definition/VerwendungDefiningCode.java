package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VerwendungDefiningCode implements EnumValueSet {
  VORLAEUFIG("Vorläufig", "Vorläufige Anschrift.", "local", "at0004"),

  HEIMAT("Heimat", "Anschrift der Heimat.", "local", "at0002"),

  ALT("Alt", "Alte Anschrift.", "local", "at0005"),

  ARBEIT("Arbeit", "Anschrift der Arbeitsstelle.", "local", "at0003");

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
