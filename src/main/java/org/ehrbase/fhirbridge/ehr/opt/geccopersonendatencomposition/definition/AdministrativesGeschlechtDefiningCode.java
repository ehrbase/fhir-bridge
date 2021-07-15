package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.KoerperstelleDefiningCode;

public enum AdministrativesGeschlechtDefiningCode implements EnumValueSet {
  UNKNOWN("Unknown", "", "http://hl7.org/fhir/administrative-gender", "unknown"),

  MALE("Male", "", "http://hl7.org/fhir/administrative-gender", "male"),

  FEMALE("Female", "", "http://hl7.org/fhir/administrative-gender", "female"),

  OTHER("Other", "", "http://hl7.org/fhir/administrative-gender", "other");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AdministrativesGeschlechtDefiningCode(String value, String description, String terminologyId,
      String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }


  public static Map<String, AdministrativesGeschlechtDefiningCode> getCodesAsMap(){
    Map<String, AdministrativesGeschlechtDefiningCode> administrativesGeschlechtDefiningCodeHashMap= new HashMap<>();
    for (AdministrativesGeschlechtDefiningCode administrativesGeschlechtDefiningCode : AdministrativesGeschlechtDefiningCode.values()) {
      administrativesGeschlechtDefiningCodeHashMap.put(administrativesGeschlechtDefiningCode.getCode(), administrativesGeschlechtDefiningCode);
    }
    return administrativesGeschlechtDefiningCodeHashMap;
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
