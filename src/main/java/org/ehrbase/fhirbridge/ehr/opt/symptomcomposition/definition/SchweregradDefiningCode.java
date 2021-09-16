package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradDefiningCode implements EnumValueSet {
  SEVERE_SEVERITY_MODIFIER_QUALIFIER_VALUE("Severe (severity modifier) (qualifier value)", "", "SNOMED Clinical Terms", "24484000"),

  LIFE_THREATENING_SEVERITY_QUALIFIER_VALUE("Life threatening severity (qualifier value)", "", "SNOMED Clinical Terms", "442452003"),

  MILD_QUALIFIER_VALUE("Mild (qualifier value)", "", "SNOMED Clinical Terms", "255604002"),

  MODERATE_SEVERITY_MODIFIER_QUALIFIER_VALUE("Moderate (severity modifier) (qualifier value)", "", "SNOMED Clinical Terms", "6736007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SchweregradDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, SchweregradDefiningCode> getCodesAsMap(){
    Map<String, SchweregradDefiningCode> stringSchweregradSymptomCodeHashMap = new HashMap<>();
    for (SchweregradDefiningCode schweregradSymptomCode : SchweregradDefiningCode.values()) {
      stringSchweregradSymptomCodeHashMap.put(schweregradSymptomCode.getCode(), schweregradSymptomCode);
    }
    return stringSchweregradSymptomCodeHashMap;
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
