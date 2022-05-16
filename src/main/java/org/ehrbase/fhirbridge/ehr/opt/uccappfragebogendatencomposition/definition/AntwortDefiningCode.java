package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KategorieDefiningCode;

public enum AntwortDefiningCode implements EnumValueSet {
  AUS_ANDEREN_GRUENDEN_BEEINTRAECHTIGT_ODER_TAETIGKEIT_NICHT_AUSGEFUEHRT("Aus anderen Gründen beeinträchtigt oder Tätigkeit nicht ausgeführt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigung", "6"),

  ZIEMLICH_BEEINTRAECHTIGT("Ziemlich beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigung", "2"),

  UEBERHAUPT_NICHT_BEEINTRAECHTIGT("Überhaupt nicht beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigung", "5"),

  SEHR_BEEINTRAECHTIGT("Sehr beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigung", "1"),

  MAESSIG_BEEINTRAECHTIGT("Mäßig beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigung", "3"),

  EIN_WENIG_BEEINTRAECHTIGT("Ein wenig beeinträchtigt", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Beeintraechtigung", "4");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AntwortDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, AntwortDefiningCode> getCodesAsMap(){
    Map<String, AntwortDefiningCode> antwortDefiningCodeHashMap = new HashMap<>();
    for (AntwortDefiningCode antwortDefiningCode : AntwortDefiningCode.values()) {
      antwortDefiningCodeHashMap.put(antwortDefiningCode.getCode(), antwortDefiningCode);
    }
    return antwortDefiningCodeHashMap;
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
