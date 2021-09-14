package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArzneimittelNameDefiningCode4 implements EnumValueSet {
  ARGATROBAN("Argatroban", "", "ATC", "B01AE03"),

  ACETYLSALICYLSAEURE("Acetylsalicylsäure", "", "ATC", "B01AC06"),

  INDOBUFEN("Indobufen", "", "ATC", "B01AC10"),

  PHENPROCOUMON("Phenprocoumon", "", "ATC", "B01AA04"),

  ABCIXIMAB("Abciximab", "", "ATC", "B01AC13"),

  CILOSTAZOL("Cilostazol", "", "ATC", "B01AC23"),

  EPOPROSTENOL("Epoprostenol", "", "ATC", "B01AC09"),

  LEPIRUDIN("Lepirudin", "", "ATC", "B01AE02"),

  ENOXAPARIN("Enoxaparin", "", "ATC", "B01AB05"),

  CLOPIDOGREL("Clopidogrel", "", "ATC", "B01AC04"),

  BETRIXABAN("Betrixaban", "", "ATC", "B01AF04"),

  DIPYRIDAMOL("Dipyridamol", "", "ATC", "B01AC07"),

  TICAGRELOR("Ticagrelor", "", "ATC", "B01AC24"),

  HEPARIN_KOMBINATIONEN("Heparin, Kombinationen", "", "ATC", "B01AB51"),

  HEPARINGRUPPE("Heparingruppe", "", "ATC", "B01AB"),

  TICLOPIDIN("Ticlopidin", "", "ATC", "B01AC05"),

  CLOPIDOGREL_UND_ACETYLSALICYLSAEURE("Clopidogrel und Acetylsalicylsäure", "", "ATC", "B01AC34"),

  NADROPARIN("Nadroparin", "", "ATC", "B01AB06"),

  TINZAPARIN("Tinzaparin", "", "ATC", "B01AB10"),

  MELAGATRAN("Melagatran", "", "ATC", "B01AE04"),

  ANTITHROMBIN_III_ANTITHROMBIN_ALFA("Antithrombin III, Antithrombin alfa", "", "ATC", "B01AB02"),

  EPTIFIBATID("Eptifibatid", "", "ATC", "B01AC16"),

  CERTOPARIN("Certoparin", "", "ATC", "B01AB13"),

  CERTOPARIN_KOMBINATIONEN("Certoparin, Kombinationen", "", "ATC", "B01AB63"),

  ILOPROST("Iloprost", "", "ATC", "B01AC11"),

  DABIGATRANETEXILAT("Dabigatranetexilat", "", "ATC", "B01AE07"),

  SULFINPYRAZON("Sulfinpyrazon", "", "ATC", "B01AC12"),

  DIREKTE_THROMBININHIBITOREN("Direkte Thrombininhibitoren", "", "ATC", "B01AE"),

  CARBASALAT_CALCIUM("Carbasalat calcium", "", "ATC", "B01AC08"),

  CANGRELOR("Cangrelor", "", "ATC", "B01AC25"),

  DIPYRIDAMOL_UND_ACETYLSALICYLSAEURE("Dipyridamol und Acetylsalicylsäure", "", "ATC", "B01AC36"),

  TIROFIBAN("Tirofiban", "", "ATC", "B01AC17"),

  TREPROSTINIL("Treprostinil", "", "ATC", "B01AC21"),

  HEPARIN("Heparin", "", "ATC", "B01AB01"),

  DIREKTE_FAKTOR_XA_INHIBITOREN("Direkte Faktor-Xa-Inhibitoren", "", "ATC", "B01AF"),

  DITAZOL("Ditazol", "", "ATC", "B01AC01"),

  BIVALIRUDIN("Bivalirudin", "", "ATC", "B01AE06"),

  EDOXABAN("Edoxaban", "", "ATC", "B01AF03"),

  KOMBINATIONEN("Kombinationen", "", "ATC", "B01AC30"),

  DANAPAROID("Danaparoid", "", "ATC", "B01AB09"),

  SELEXIPAG("Selexipag", "", "ATC", "B01AC27"),

  ALOXIPRIN("Aloxiprin", "", "ATC", "B01AC15"),

  ACETYLSALICYLSAEURE_UND_ESOMEPRAZOL("Acetylsalicylsäure und Esomeprazol", "", "ATC", "B01AC86"),

  SULODEXID("Sulodexid", "", "ATC", "B01AB11"),

  VORAPAXAR("Vorapaxar", "", "ATC", "B01AC26"),

  CLORICROMEN("Cloricromen", "", "ATC", "B01AC02"),

  ACETYLSALICYLSAEURE_KOMBINATIONEN_MIT_PROTONENPUMPENHEMMERN("Acetylsalicylsäure, Kombinationen mit Protonenpumpenhemmern", "", "ATC", "B01AC56"),

  APIXABAN("Apixaban", "", "ATC", "B01AF02"),

  BERAPROST("Beraprost", "", "ATC", "B01AC19"),

  DESIRUDIN("Desirudin", "", "ATC", "B01AE01"),

  PRASUGREL("Prasugrel", "", "ATC", "B01AC22"),

  BEMIPARIN("Bemiparin", "", "ATC", "B01AB12"),

  XIMELAGATRAN("Ximelagatran", "", "ATC", "B01AE05"),

  RIVAROXABAN("Rivaroxaban", "", "ATC", "B01AF01"),

  DALTEPARIN("Dalteparin", "", "ATC", "B01AB04"),

  TRIFLUSAL("Triflusal", "", "ATC", "B01AC18"),

  REVIPARIN("Reviparin", "", "ATC", "B01AB08"),

  PARNAPARIN("Parnaparin", "", "ATC", "B01AB07"),

  THROMBOZYTENAGGREGATIONSHEMMER_EXKL_HEPARIN("Thrombozytenaggregationshemmer, exkl. Heparin", "", "ATC", "B01AC"),

  PICOTAMID("Picotamid", "", "ATC", "B01AC03");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArzneimittelNameDefiningCode4(String value, String description, String terminologyId,
      String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, ArzneimittelNameDefiningCode4> getCodesAsMap() {
    Map<String, ArzneimittelNameDefiningCode4> arzneimittelNameDefiningCodeHashMap = new HashMap<>();
    for (ArzneimittelNameDefiningCode4 arzneimittelNameDefiningCode : ArzneimittelNameDefiningCode4.values()) {
      arzneimittelNameDefiningCodeHashMap.put(arzneimittelNameDefiningCode.getCode(), arzneimittelNameDefiningCode);
    }
    return arzneimittelNameDefiningCodeHashMap;
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
