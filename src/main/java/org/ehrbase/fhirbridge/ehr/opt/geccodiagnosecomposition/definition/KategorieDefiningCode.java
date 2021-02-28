package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieDefiningCode implements EnumValueSet {
  PULMONARYMEDICINE("pulmonaryMedicine", "pulmonaryMedicine", "SNOMED Clinical Terms", "418112009"),

  ONCOLOGY("Oncology", "Oncology", "SNOMED Clinical Terms", "394593009"),

  IMMUNOLOGY("immunology", "immunology", "SNOMED Clinical Terms", "408480009"),

  NEPHROLOGY_QUALIFIER_VALUE("Nephrology (qualifier value)", "Nephrology (qualifier value)", "SNOMED Clinical Terms", "394589003"),

  COMPLICATION_DISORDER("Complication (disorder)", "Complication (disorder)", "SNOMED Clinical Terms", "116223007"),

  HEPATOLOGY("hepatology", "hepatology", "SNOMED Clinical Terms", "408472002"),

  TRANSPLANT_MEDICINE("Transplant medicine", "Transplant medicine", "SNOMED Clinical Terms", "788415003"),

  GASTROENTEROLOGY("Gastroenterology", "Gastroenterology", "SNOMED Clinical Terms", "394584008"),

  INFECTIOUSDISEASES("infectiousDiseases", "infectiousDiseases", "SNOMED Clinical Terms", "394807007"),

  PSYCHIATRY("Psychiatry", "Psychiatry", "SNOMED Clinical Terms", "394587001"),

  RHEUMATOLOTY("rheumatoloty", "rheumatoloty", "SNOMED Clinical Terms", "394810000"),

  VENTILATION_STATUS_OBSERVABLE_ENTITY("Ventilation status (observable entity)", "Ventilation status (observable entity)", "SNOMED Clinical Terms", "404989005"),

  VASCULARMEDICINE("vascularMedicine", "vascularMedicine", "SNOMED Clinical Terms", "722414000"),

  DIABETIC_MEDICINE("Diabetic Medicine", "Diabetic Medicine", "SNOMED Clinical Terms", "408475000"),

  NEUROLOGY("Neurology", "Neurology", "SNOMED Clinical Terms", "394591006");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KategorieDefiningCode(String value, String description, String terminologyId, String code) {
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
