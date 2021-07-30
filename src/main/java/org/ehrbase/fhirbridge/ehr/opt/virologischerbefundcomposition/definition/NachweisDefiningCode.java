package org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NachweisDefiningCode implements EnumValueSet {
  NOT_DETECTED_QUALIFIER_VALUE("Not detected (qualifier value)", "", "SNOMED Clinical Terms", "260415000"),

  INCONCLUSIVE_QUALIFIER_VALUE("Inconclusive (qualifier value)", "", "SNOMED Clinical Terms", "419984006"),

  DETECTED_QUALIFIER_VALUE("Detected (qualifier value)", "", "SNOMED Clinical Terms", "260373001");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NachweisDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, NachweisDefiningCode> getCodesAsMap(){
    Map<String, NachweisDefiningCode> DefiningCodeHashMap = new HashMap<>();
    for (NachweisDefiningCode DefiningCode : NachweisDefiningCode.values()) {
      DefiningCodeHashMap.put(DefiningCode.getCode(), DefiningCode);
    }
    return DefiningCodeHashMap;
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
