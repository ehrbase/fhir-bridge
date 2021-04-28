package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArzneimittelNameDefiningCode3 implements EnumValueSet {
  DIPHTHERIE_IMMUNGLOBULIN("Diphtherie-Immunglobulin", "", "ATC", "J06BB10"),

  FSME_IMMUNGLOBULIN("FSME-Immunglobulin", "", "ATC", "J06BB12"),

  RAXIBACUMAB("Raxibacumab", "", "ATC", "J06BB18"),

  HEPATITIS_B_IMMUNGLOBULIN("Hepatitis-B-Immunglobulin", "", "ATC", "J06BB04"),

  MUMPS_IMMUNGLOBULIN("Mumps-Immunglobulin", "", "ATC", "J06BB15"),

  OBILTOXAXIMAB("Obiltoxaximab", "", "ATC", "J06BB22"),

  IMMUNGLOBULINE_NORMAL_HUMAN("Immunglobuline, normal human", "", "ATC", "J06BA"),

  PERTUSSIS_IMMUNGLOBULIN("Pertussis-Immunglobulin", "", "ATC", "J06BB13"),

  ANDERE_IMMUNGLOBULINE("Andere Immunglobuline", "", "ATC", "J06BC10"),

  HEPATITIS_A_IMMUNGLOBULIN("Hepatitis-A-Immunglobulin", "", "ATC", "J06BB11"),

  IMMUNGLOBULINE("IMMUNGLOBULINE", "", "ATC", "J06B"),

  CYTOMEGALIEVIRUS_IMMUNGLOBULIN("Cytomegalievirus-Immunglobulin", "", "ATC", "J06BB09"),

  BEZLOTOXUMAB("Bezlotoxumab", "", "ATC", "J06BB21"),

  KOMBINATIONEN("Kombinationen", "", "ATC", "J06BB30"),

  ANTHRAX_IMMUNGLOBULIN("Anthrax-Immunglobulin", "", "ATC", "J06BB19"),

  ROETELN_IMMUNGLOBULIN("Röteln-Immunglobulin", "", "ATC", "J06BB06"),

  MOTAVIZUMAB("Motavizumab", "", "ATC", "J06BB17"),

  ANTI_D_RH_IMMUNGLOBULIN("Anti-D(rh)-Immunglobulin", "", "ATC", "J06BB01"),

  IMMUNGLOBULINE_NORMAL_HUMAN_ZUR_INTRAVASALEN_ANWENDUNG("Immunglobuline, normal human, zur intravasalen Anwendung", "", "ATC", "J06BA02"),

  TOLLWUT_IMMUNGLOBULIN("Tollwut-Immunglobulin", "", "ATC", "J06BB05"),

  PALIVIZUMAB("Palivizumab", "", "ATC", "J06BB16"),

  SPEZIFISCHE_IMMUNGLOBULINE("Spezifische Immunglobuline", "", "ATC", "J06BB"),

  STAPHYLOCOCCUS_IMMUNGLOBULIN("Staphylococcus-Immunglobulin", "", "ATC", "J06BB08"),

  IMMUNGLOBULINE_NORMAL_HUMAN_ZUR_EXTRAVASALEN_ANWENDUNG("Immunglobuline, normal human, zur extravasalen Anwendung", "", "ATC", "J06BA01"),

  KUHPOCKEN_IMMUNGLOBULIN("Kuhpocken-Immunglobulin", "", "ATC", "J06BB07"),

  NEBACUMAB("Nebacumab", "", "ATC", "J06BC01"),

  TETANUS_IMMUNGLOBULIN("Tetanus-Immunglobulin", "", "ATC", "J06BB02"),

  MASERN_IMMUNGLOBULIN("Masern-Immunglobulin", "", "ATC", "J06BB14"),

  VARICELLA_ZOSTER_IMMUNGLOBULIN("Varicella/Zoster-Immunglobulin", "", "ATC", "J06BB03");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArzneimittelNameDefiningCode3(String value, String description, String terminologyId,
      String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, ArzneimittelNameDefiningCode3> getCodesAsMap() {
      Map<String, ArzneimittelNameDefiningCode3> arzneimittelNameDefiningCodeHashMap = new HashMap<>();
      for (ArzneimittelNameDefiningCode3 arzneimittelNameDefiningCode : ArzneimittelNameDefiningCode3.values()) {
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
