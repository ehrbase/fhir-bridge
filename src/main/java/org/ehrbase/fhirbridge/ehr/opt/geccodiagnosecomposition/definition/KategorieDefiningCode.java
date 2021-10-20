package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieDefiningCode implements EnumValueSet {
  PULMONARYMEDICINE("PulmonaryMedicine", "", "SNOMED Clinical Terms", "418112009"),

  HEPATOLOGY("Hepatology", "", "SNOMED Clinical Terms", "408472002"),

  TRANSPLANT_MEDICINE("Transplant medicine", "", "SNOMED Clinical Terms", "788415003"),

  PSYCHIATRY("Psychiatry", "", "SNOMED Clinical Terms", "394587001"),

  INFECTIOUSDISEASES("InfectiousDiseases", "", "SNOMED Clinical Terms", "394807007"),

  RHEUMATOLOTY("Rheumatoloty", "", "SNOMED Clinical Terms", "394810000"),

  DIABETIC_MEDICINE("Diabetic Medicine", "", "SNOMED Clinical Terms", "408475000"),

  NEUROLOGY("Neurology", "", "SNOMED Clinical Terms", "394591006"),

  IMMUNOLOGY("Immunology", "", "SNOMED Clinical Terms", "408480009"),

  NEPHROLOGY_QUALIFIER_VALUE("Nephrology (qualifier value)", "", "SNOMED Clinical Terms", "394589003"),

  COMPLICATION_DISORDER("Complication (disorder)", "", "SNOMED Clinical Terms", "116223007"),

  VASCULARMEDICINE("VascularMedicine", "", "SNOMED Clinical Terms", "722414000"),

  ONCOLOGY("Oncology", "", "SNOMED Clinical Terms", "394593009"),

  GASTROENTEROLOGY("Gastroenterology", "", "SNOMED Clinical Terms", "394584008"),

  VENTILATION_STATUS_OBSERVABLE_ENTITY("Ventilation status (observable entity)", "", "SNOMED Clinical Terms", "404989005");

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

  public static Map<String, KategorieDefiningCode> getCodesAsMap(){
    Map<String, KategorieDefiningCode> kategorieDefiningCodeHashMap = new HashMap<>();
    for (KategorieDefiningCode kategorieDefiningCode : KategorieDefiningCode.values()) {
      kategorieDefiningCodeHashMap.put(kategorieDefiningCode.getCode(), kategorieDefiningCode);
    }
    return kategorieDefiningCodeHashMap;
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
