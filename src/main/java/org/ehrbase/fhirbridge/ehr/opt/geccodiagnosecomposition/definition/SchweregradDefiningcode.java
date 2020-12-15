package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradDefiningcode implements EnumValueSet {
  N442452003("442452003", "442452003", "SNOMED Clinical Terms", "442452003"),

  N371924009("371924009", "371924009", "SNOMED Clinical Terms", "371924009"),

  N261665006("261665006", "261665006", "SNOMED Clinical Terms", "261665006"),

  N371923003("371923003", "371923003", "SNOMED Clinical Terms", "371923003"),

  N399166001("399166001", "399166001", "SNOMED Clinical Terms", "399166001"),

  N24484000("24484000", "24484000", "SNOMED Clinical Terms", "24484000"),

  N255604002("255604002", "255604002", "SNOMED Clinical Terms", "255604002"),

  N6736007("6736007", "6736007", "SNOMED Clinical Terms", "6736007"),

  N277022003("277022003", "277022003", "SNOMED Clinical Terms", "277022003");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SchweregradDefiningcode(String value, String description, String terminologyId, String code) {
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
