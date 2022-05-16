package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AntwortDefiningCode6 implements EnumValueSet {
  GROESSTENTEILS_UNZUFRIEDEN("Größtenteils unzufrieden", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Zufriedenheit", "2"),

  GROESSTENTEILS_ZUFRIEDEN("Größtenteils zufrieden", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Zufriedenheit", "4"),

  VOLLKOMMEN_UNZUFRIEDEN("Vollkommen unzufrieden", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Zufriedenheit", "1"),

  VOLLKOMMEN_ZUFRIEDEN("Vollkommen zufrieden", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Zufriedenheit", "5"),

  WEDER_ZUFRIEDEN_NOCH_UNZUFRIEDEN("Weder zufrieden noch unzufrieden", "", "http://www.columbiaheartvalve.org/KCCQ12/system/code/Zufriedenheit", "3");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AntwortDefiningCode6(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, AntwortDefiningCode6> getCodesAsMap(){
    Map<String, AntwortDefiningCode6> antwortDefining6CodeHashMap = new HashMap<>();
    for (AntwortDefiningCode6 antwortDefiningCode6 : AntwortDefiningCode6.values()) {
      antwortDefining6CodeHashMap.put(antwortDefiningCode6.getCode(), antwortDefiningCode6);
    }
    return antwortDefining6CodeHashMap;
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
