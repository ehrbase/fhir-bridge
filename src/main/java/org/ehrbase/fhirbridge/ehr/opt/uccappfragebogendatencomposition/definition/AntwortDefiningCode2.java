package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AntwortDefiningCode2 implements EnumValueSet {
  N1_BIS2_MAL_PRO_WOCHE("1- bis 2-mal pro Woche", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf1", "3"),

  JEDEN_MORGEN("Jeden Morgen", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf1", "1"),

  WENIGER_ALS_EINMAL_PRO_WOCHE("Weniger als einmal pro Woche", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf1", "4"),

  N3_MAL_PRO_WOCHE_ODER_OEFTER_ABER_NICHT_JEDEN_TAG("3-mal pro Woche oder öfter, aber nicht jeden Tag", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf1", "2"),

  NIEMALS_WAEHREND_DER_LETZTEN2_WOCHEN("Niemals während der letzten 2 Wochen", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf1", "5");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AntwortDefiningCode2(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, AntwortDefiningCode2> getCodesAsMap(){
    Map<String, AntwortDefiningCode2> antwortDefining2CodeHashMap = new HashMap<>();
    for (AntwortDefiningCode2 antwortDefiningCode2 : AntwortDefiningCode2.values()) {
      antwortDefining2CodeHashMap.put(antwortDefiningCode2.getCode(), antwortDefiningCode2);
    }
    return antwortDefining2CodeHashMap;
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
