package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AntwortDefiningCode4 implements EnumValueSet {
  NIEMALS_WAEHREND_DER_LETZTEN2_WOCHEN("Niemals während der letzten 2 Wochen", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf3", "h3_5"),

  JEDE_NACHT("Jede Nacht", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf3", "h3_1"),

  N1_BIS2_MAL_PRO_WOCHE("1-bis 2-mal pro Woche", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf3", "h3_3"),

  N3_MAL_PRO_WOCHE_ODER_OEFTER_ABER_NICHT_JEDE_NACHT("3-mal pro Woche oder öfter, aber nicht jede Nacht", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf3", "h3_2"),

  WENIGER_ALS_EINMAL_PRO_WOCHE("Weniger als einmal pro Woche", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf3", "h3_4");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AntwortDefiningCode4(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  } 
  
  public static Map<String, AntwortDefiningCode4> getCodesAsMap(){
    Map<String, AntwortDefiningCode4> antwortDefining4CodeHashMap = new HashMap<>();
    for (AntwortDefiningCode4 antwortDefiningCode4 : AntwortDefiningCode4.values()) {
      antwortDefining4CodeHashMap.put(antwortDefiningCode4.getCode(), antwortDefiningCode4);
    }
    return antwortDefining4CodeHashMap;
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
