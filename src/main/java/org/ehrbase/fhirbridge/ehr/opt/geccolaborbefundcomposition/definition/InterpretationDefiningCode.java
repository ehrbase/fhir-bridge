package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum InterpretationDefiningCode implements EnumValueSet {
  CARRIER("Carrier", "", "http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "CAR"),

  SIGNIFICANT_CHANGE_DOWN("Significant change down", "", "http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "D"),

  OFF_SCALE_LOW("Off scale low", "", "http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "<"),

  WORSE("Worse", "", "http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "W"),

  OFF_SCALE_HIG("Off scale hig", "", "http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", ">"),

  SIGNIFICANT_CHANGE_UP("Significant change up", "", "http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "U"),

  BETTER("Better", "", "http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "B");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  InterpretationDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, InterpretationDefiningCode> getCodesAsMap(){
    Map<String, InterpretationDefiningCode> interpretationDefiningCodeHashMap = new HashMap<>();
    for (InterpretationDefiningCode interpretationDefiningCode : InterpretationDefiningCode.values()) {
      interpretationDefiningCodeHashMap.put(interpretationDefiningCode.getCode(), interpretationDefiningCode);
    }
    return interpretationDefiningCodeHashMap;
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
