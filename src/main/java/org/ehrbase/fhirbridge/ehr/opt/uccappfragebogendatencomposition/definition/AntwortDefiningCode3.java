package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AntwortDefiningCode3 implements EnumValueSet {
  WENIGER_ALS_EINMAL_PRO_WOCHE("Weniger als einmal pro Woche", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf2", "6"),

  N1_BIS2_MAL_PRO_WOCHE("1- bis 2-mal pro Woche", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf2", "5"),

  MEHRMALS_AM_TAG("Mehrmals  am Tag", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf2", "2"),

  N3_MAL_PRO_WOCHE_ODER_OEFTER_ABER_NICHT_JEDEN_TAG("3-mal pro Woche oder öfter, aber nicht jeden Tag", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf2", "4"),

  NIEMALS_WAEHREND_DER_LETZTEN2_WOCHEN("Niemals während der letzten 2 Wochen", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf2", "7"),

  STAENDIG("Ständig", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf2", "1"),

  MINDESTENS_EINMAL_AM_TAG("Mindestens einmal am Tag", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Haeuf2", "3");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AntwortDefiningCode3(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, AntwortDefiningCode3> getCodesAsMap(){
    Map<String, AntwortDefiningCode3> antwortDefining3CodeHashMap = new HashMap<>();
    for (AntwortDefiningCode3 antwortDefiningCode3 : AntwortDefiningCode3.values()) {
      antwortDefining3CodeHashMap.put(antwortDefiningCode3.getCode(), antwortDefiningCode3);
    }
    return antwortDefining3CodeHashMap;
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
