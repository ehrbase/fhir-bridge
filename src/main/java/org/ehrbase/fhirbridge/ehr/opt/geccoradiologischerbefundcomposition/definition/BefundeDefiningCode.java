package org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BefundeDefiningCode implements EnumValueSet {
  COVID19_TYPISCHER_BEFUND("COVID-19-typischer Befund", "COVID-19-typischer Befund", "SNOMED-CT", "840539006"),

  NORMALBEFUND("Normalbefund", "Normalbefund", "SNOMED-CT", "17621005"),

  UNSPEZIFISCHER_BEFUND("Unspezifischer Befund", "Unspezifischer Befund", "SNOMED-CT", "373068000");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BefundeDefiningCode(String value, String description, String terminologyId, String code) {
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
