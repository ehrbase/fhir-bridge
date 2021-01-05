package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieDefiningcode implements EnumValueSet {
  N116223007("116223007", "116223007", "SNOMED Clinical Terms", "116223007"),

  N418112009("418112009", "418112009", "SNOMED Clinical Terms", "418112009"),

  N394584008("394584008", "394584008", "SNOMED Clinical Terms", "394584008"),

  N394591006("394591006", "394591006", "SNOMED Clinical Terms", "394591006"),

  N394589003("394589003", "394589003", "SNOMED Clinical Terms", "394589003"),

  N408480009("408480009", "408480009", "SNOMED Clinical Terms", "408480009"),

  N394807007("394807007", "394807007", "SNOMED Clinical Terms", "394807007"),

  N394593009("394593009", "394593009", "SNOMED Clinical Terms", "394593009"),

  N722414000("722414000", "722414000", "SNOMED Clinical Terms", "722414000"),

  N408475000("408475000", "408475000", "SNOMED Clinical Terms", "408475000"),

  N788415003("788415003", "788415003", "SNOMED Clinical Terms", "788415003"),

  N394587001("394587001", "394587001", "SNOMED Clinical Terms", "394587001"),

  N408472002("408472002", "408472002", "SNOMED Clinical Terms", "408472002"),

  N394810000("394810000", "394810000", "SNOMED Clinical Terms", "394810000");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KategorieDefiningcode(String value, String description, String terminologyId, String code) {
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
