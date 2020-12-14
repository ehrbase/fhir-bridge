package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieDefiningcode implements EnumValueSet {
  VASCULARMEDICINE("vascularMedicine", null, "SNOMED Clinical Terms", "722414000"),

  PULMONARYMEDICINE("pulmonaryMedicine", null, "SNOMED Clinical Terms", "418112009"),

  TRANSPLANT_MEDICINE("Transplant medicine", null, "SNOMED Clinical Terms", "788415003"),

  COMPLICATION_DISORDER("Complication (disorder)", null, "SNOMED Clinical Terms", "116223007"),

  DIABETIC_MEDICINE("Diabetic Medicine", null, "SNOMED Clinical Terms", "408475000"),

  RHEUMATOLOTY("rheumatoloty", null, "SNOMED Clinical Terms", "394810000"),

  INFECTIOUSDISEASES("infectiousDiseases", null, "SNOMED Clinical Terms", "394807007"),

  IMMUNOLOGY("immunology", null, "SNOMED Clinical Terms", "408480009"),

  HEPATOLOGY("hepatology", null, "SNOMED Clinical Terms", "408472002"),

  ONCOLOGY("Oncology", null, "SNOMED Clinical Terms", "394593009"),

  GASTROENTEROLOGY("Gastroenterology", null, "SNOMED Clinical Terms", "394584008");

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
