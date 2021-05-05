package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArzneimittelNameDefiningCode implements EnumValueSet {
  PRODUCT_CONTAINING_ATAZANAVIR_MEDICINAL_PRODUCT("Product containing atazanavir (medicinal product)", "", "SNOMED CT", "413591007"),

  PRODUCT_CONTAINING_STEROID_PRODUCT("Product containing steroid (product)", "", "SNOMED CT", "768759001"),

  PRODUCT_CONTAINING_HYDROXYCHLOROQUINE_MEDICINAL_PRODUCT("Product containing hydroxychloroquine (medicinal product)", "", "SNOMED CT", "83490000"),

  PRODUCT_CONTAINING_GANCICLOVIR_MEDICINAL_PRODUCT("Product containing ganciclovir (medicinal product)", "", "SNOMED CT", "78025001"),

  PRODUCT_CONTAINING_INTERLEUKIN1_RECEPTOR_ANTAGONIST_PRODUCT("Product containing interleukin 1 receptor antagonist (product)", "", "SNOMED CT", "430817009"),

  PRODUCT_CONTAINING_COLCHICINE_MEDICINAL_PRODUCT("Product containing colchicine (medicinal product)", "", "SNOMED CT", "73133000"),

  PRODUCT_CONTAINING_IVERMECTIN_MEDICINAL_PRODUCT("Product containing ivermectin (medicinal product)", "", "SNOMED CT", "96138006"),

  PRODUCT_CONTAINING_RUXOLITINIB_MEDICINAL_PRODUCT("Product containing ruxolitinib (medicinal product)", "", "SNOMED CT", "703779004"),

  PRODUCT_CONTAINING_CALCINEURIN_INHIBITOR_PRODUCT("Product containing calcineurin inhibitor (product)", "", "SNOMED CT", "416587008"),

  PRODUCT_CONTAINING_DARUNAVIR_MEDICINAL_PRODUCT("Product containing darunavir (medicinal product)", "", "SNOMED CT", "424096001"),

  PRODUCT_CONTAINING_INTERFERON_PRODUCT("Product containing interferon (product)", "", "SNOMED CT", "768865007"),

  PRODUCT_CONTAINING_CHLOROQUINE_MEDICINAL_PRODUCT("Product containing chloroquine (medicinal product)", "", "SNOMED CT", "14728000"),

  PRODUCT_CONTAINING_OSELTAMIVIR_MEDICINAL_PRODUCT("Product containing oseltamivir (medicinal product)", "", "SNOMED CT", "386142008"),

  PRODUCT_CONTAINING_REMDESIVIR_MEDICINAL_PRODUCT("Product containing remdesivir (medicinal product)", "", "SNOMED CT", "870518005"),

  PRODUCT_CONTAINING_ZINC_MEDICINAL_PRODUCT("Product containing zinc (medicinal product)", "", "SNOMED CT", "764877006"),

  PRODUCT_CONTAINING_ANGIOTENSIN_CONVERTING_ENZYME_INHIBITOR_PRODUCT("Product containing angiotensin-converting enzyme inhibitor (product)", "", "SNOMED CT", "41549009"),

  PRODUCT_CONTAINING_TOCILIZUMAB_MEDICINAL_PRODUCT("Product containing tocilizumab (medicinal product)", "", "SNOMED CT", "444649004"),

  PRODUCT_CONTAINING_SARILUMAB_MEDICINAL_PRODUCT("Product containing sarilumab (medicinal product)", "", "SNOMED CT", "763522001"),

  PRODUCT_CONTAINING_TUMOR_NECROSIS_FACTOR_ALPHA_INHIBITOR_PRODUCT("Product containing tumor necrosis factor alpha inhibitor (product)", "", "SNOMED CT", "416897008"),

  PRODUCT_CONTAINING_CALCIFEDIOL_MEDICINAL_PRODUCT("Product containing calcifediol (medicinal product)", "", "SNOMED CT", "88519001"),

  PRODUCT_CONTAINING_RIBAVIRIN_MEDICINAL_PRODUCT("Product containing ribavirin (medicinal product)", "", "SNOMED CT", "35063004"),

  PRODUCT_CONTAINING_LOPINAVIR_AND_RITONAVIR_MEDICINAL_PRODUCT("Product containing lopinavir and ritonavir (medicinal product)", "", "SNOMED CT", "134573001");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArzneimittelNameDefiningCode(String value, String description, String terminologyId,
      String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, ArzneimittelNameDefiningCode> getCodesAsMap(){
    Map<String, ArzneimittelNameDefiningCode> arzneimittelNameDefiningCodeHashMap = new HashMap<>();
    for (ArzneimittelNameDefiningCode arzneimittelNameDefiningCode : ArzneimittelNameDefiningCode.values()) {
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
