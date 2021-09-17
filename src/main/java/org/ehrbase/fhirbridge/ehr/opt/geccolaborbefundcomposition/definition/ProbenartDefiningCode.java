package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ProbenartDefiningCode implements EnumValueSet {
  CYST_BAKER_S("Cyst, Baker's", "", "HL7", "BCYST"),

  AMNIOTIC_FLUID("Amniotic fluid", "", "HL7", "AMN"),

  BRUSH("Brush", "", "HL7", "BRSH"),

  CARBUNCLE("Carbuncle", "", "HL7", "CARBU"),

  BITE_CAT("Bite, Cat", "", "HL7", "CBITE"),

  CATHETER_INSERTION_SITE("Catheter Insertion Site", "", "HL7", "CSITE"),

  CYST("Cyst", "", "HL7", "CYST"),

  CATHETER_TIP_ARTERIAL("Catheter Tip, Arterial", "", "HL7", "ARTC"),

  BIOSPY_CONE("Biospy, Cone", "", "HL7", "CONE"),

  BOWEL_CONTENTS("Bowel contents", "", "HL7", "BOWL"),

  BLEB("Bleb", "", "HL7", "BLEB"),

  CERVICAL_MUCUS("Cervical Mucus", "", "HL7", "CVM"),

  BITE("Bite", "", "HL7", "BITE"),

  BULLA_BULLAE("Bulla/Bullae", "", "HL7", "BULLA"),

  SCRATCH_CAT("Scratch, Cat", "", "HL7", "CSCR"),

  AUTOPSY("Autopsy", "", "HL7", "AUTP"),

  BLOOD_VENOUS("Blood venous", "", "HL7", "BLDV"),

  CEREBRAL_SPINAL_FLUID("Cerebral spinal fluid", "", "HL7", "CSF"),

  CATHETER_TIP_CVP("Catheter Tip, CVP", "", "HL7", "CVPT"),

  BASOPHILS("Basophils", "", "HL7", "BPH"),

  ENVIRONMENTAL_AUTOCLAVE_AMPULE("Environmental, Autoclave Ampule", "", "HL7", "AUTOA"),

  BLOOD_CELL_SAVER("Blood, Cell Saver", "", "HL7", "CSVR"),

  FLUID_CYSTOSTOMY_TUBE("Fluid, Cystostomy Tube", "", "HL7", "CSMY"),

  BITE_DOG("Bite, Dog", "", "HL7", "DBITE"),

  BONE("Bone", "", "HL7", "BONE"),

  COLOSTRUM("Colostrum", "", "HL7", "COL"),

  SERUM_CONVALESCENT("Serum, Convalescent", "", "HL7", "CSERU"),

  WHOLE_BLOOD("Whole blood", "", "HL7", "BLD"),

  ALLOGRAFT("Allograft", "", "HL7", "ALL"),

  BUBO("Bubo", "", "HL7", "BUB"),

  CLIPPINGS("Clippings", "", "HL7", "CLIPP"),

  AIR_SAMPLE("Air Sample", "", "HL7", "AIRS"),

  WHOLE_BODY("Whole body", "", "HL7", "BDY"),

  BOIL("Boil", "", "HL7", "BOIL"),

  CANNULA("Cannula", "", "HL7", "CNL"),

  CATHETER_TIP_ANGIO("Catheter Tip, Angio", "", "HL7", "ANGI"),

  BLISTER("Blister", "", "HL7", "BLIST"),

  BRUSHING("Brushing", "", "HL7", "BRUS"),

  ENVIRONMENT_ATTEST("Environment, Attest", "", "HL7", "ATTE"),

  BLOOD_ARTERIAL("Blood arterial", "", "HL7", "BLDA"),

  CORD_BLOOD("Cord blood", "", "HL7", "BLDCO"),

  FLUID_CYST("Fluid, Cyst", "", "HL7", "CST"),

  CATHETER("Catheter", "", "HL7", "CAT"),

  BLOOD_PRODUCT_UNIT("Blood product unit", "", "HL7", "BPU"),

  SPUTUM_DEEP_COUG("Sputum, Deep Coug", "", "HL7", "DCS"),

  TISSUE_ACNE("Tissue, Acne", "", "HL7", "ACNE"),

  NODULE_CYSTIC("Nodule, Cystic", "", "HL7", "CYN"),

  FLUID_ACNE("Fluid, Acne", "", "HL7", "ACNFLD"),

  CARDIAC_MUSCLE("Cardiac muscle", "", "HL7", "CDM"),

  AMPUTATION("Amputation", "", "HL7", "AMP"),

  ENVIRONMENTAL_AUTOCLAVE_CAPSULE("Environmental, Autoclave Capsule", "", "HL7", "AUTOC"),

  SERUM_ACUTE("Serum, Acute", "", "HL7", "ASERU"),

  CURRETAGE("Curretage", "", "HL7", "CUR"),

  ASPIRATE("Aspirate", "", "HL7", "ASP"),

  BURN("Burn", "", "HL7", "BRN"),

  BLOOD_BAG("Blood bag", "", "HL7", "BBL"),

  BILE_FLUID("Bile Fluid", "", "HL7", "BIFL"),

  CONJUNCTIVA("Conjunctiva", "", "HL7", "CNJT"),

  BREATH_USE_EXHLD("Breath (use EXHLD)", "", "HL7", "BRTH"),

  CATHETER_TIP("Catheter tip", "", "HL7", "CTP"),

  SITE_CVP("Site, CVP", "", "HL7", "CVPS"),

  ABSCESS("Abscess", "", "HL7", "ABS"),

  BIOPSY("Biopsy", "", "HL7", "BX"),

  CALCULUS_STONE("Calculus (=Stone)", "", "HL7", "CALC");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ProbenartDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, ProbenartDefiningCode> getCodesAsMap(){
    Map<String, ProbenartDefiningCode> probenartDefiningCodeHashMap = new HashMap<>();
    for (ProbenartDefiningCode probenartDefiningCode : ProbenartDefiningCode.values()) {
      probenartDefiningCodeHashMap.put(probenartDefiningCode.getCode(), probenartDefiningCode);
    }
    return probenartDefiningCodeHashMap;
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
