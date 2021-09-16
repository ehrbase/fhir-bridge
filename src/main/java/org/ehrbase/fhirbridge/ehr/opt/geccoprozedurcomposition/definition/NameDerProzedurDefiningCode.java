package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDerProzedurDefiningCode implements EnumValueSet {
  APHERESIS_PROCEDURE("Apheresis (procedure)", "", "SNOMED Clinical Terms", "127788007"),

  DIAGNOSTIC_ULTRASONOGRAPHY_PROCEDURE("Diagnostic ultrasonography (procedure)", "", "SNOMED Clinical Terms", "16310003"),

  THERAPEUTIC_PROCEDURE_PROCEDURE("Therapeutic procedure (procedure)", "", "SNOMED Clinical Terms", "277132007"),

  PLAIN_RADIOGRAPHY("Plain radiography", "", "SNOMED Clinical Terms", "168537006"),

  EXTRACORPOREAL_MEMBRANE_OXYGENATION_PROCEDURE("Extracorporeal membrane oxygenation (procedure)", "", "SNOMED Clinical Terms", "233573008"),

  RESPIRATORY_THERAPY_PROCEDURE("Respiratory therapy (procedure)", "", "SNOMED Clinical Terms", "53950000"),

  COMPUTERIZED_AXIAL_TOMOGRAPHY_PROCEDURE("Computerized axial tomography (procedure)", "", "SNOMED Clinical Terms", "77477000"),

  DIALYSIS_PROCEDURE_PROCEDURE("Dialysis procedure (procedure)", "", "SNOMED Clinical Terms", "108241001"),

  ARTIFICIAL_RESPIRATION_PROCEDURE("Artificial respiration (procedure)", "", "SNOMED Clinical Terms", "40617009"),

  OXYGEN_ADMINISTRATION_BY_NASAL_CANNULA_PROCEDURE("Oxygen administration by nasal cannula (procedure)", "", "SNOMED Clinical Terms", "371907003"),

  PLACING_SUBJECT_IN_PRONE_POSITION_PROCEDURE("Placing subject in prone position (procedure)", "", "SNOMED Clinical Terms", "431182000"),

  NONINVASIVE_VENTILATION_PROCEDURE("Noninvasive ventilation (procedure)", "", "SNOMED Clinical Terms", "428311008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDerProzedurDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, NameDerProzedurDefiningCode> getCodesAsMap(){
    Map<String, NameDerProzedurDefiningCode> nameDerProzedurDefiningCodeCodeHashMap = new HashMap<>();
    for (NameDerProzedurDefiningCode nameDerProzedurDefiningCode : NameDerProzedurDefiningCode.values()) {
      nameDerProzedurDefiningCodeCodeHashMap.put(nameDerProzedurDefiningCode.getCode(), nameDerProzedurDefiningCode);
    }
    return nameDerProzedurDefiningCodeCodeHashMap;
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
