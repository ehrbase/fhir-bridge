package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AntwortDefiningCode5 implements EnumValueSet {
  ZIEMLICH_BEEINTRAECHTIGT("Ziemlich beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigungkurz", "2"),

  UEBERHAUPT_NICHT_BEEINTRAECHTIGT("Überhaupt nicht beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigungkurz", "5"),

  SEHR_BEEINTRAECHTIGT("Sehr beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigungkurz", "1"),

  MAESSIG_BEEINTRAECHTIGT("Mäßig beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigungkurz", "3"),

  EIN_WENIG_BEEINTRAECHTIGT("Ein wenig beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigungkurz", "4");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AntwortDefiningCode5(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, AntwortDefiningCode5> getCodesAsMap(){
    Map<String, AntwortDefiningCode5> antwortDefining5CodeHashMap = new HashMap<>();
    for (AntwortDefiningCode5 antwortDefiningCode5 : AntwortDefiningCode5.values()) {
      antwortDefining5CodeHashMap.put(antwortDefiningCode5.getCode(), antwortDefiningCode5);
    }
    return antwortDefining5CodeHashMap;
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
