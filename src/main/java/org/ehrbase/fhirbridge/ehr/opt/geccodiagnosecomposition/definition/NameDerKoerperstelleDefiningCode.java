package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDerKoerperstelleDefiningCode implements EnumValueSet {
  CEREBRAL_MENINGES_STRUCTURE_BODY_STRUCTURE("Cerebral meninges structure (body structure)", "", "SNOMED Clinical Terms", "8935007"),

  BONE_TISSUE_STRUCTURE_BODY_STRUCTURE("Bone (tissue) structure (body structure)", "", "SNOMED Clinical Terms", "3138006"),

  ENTIRE_SMALL_INTESTINE_BODY_STRUCTURE("Entire small intestine (body structure)", "", "SNOMED Clinical Terms", "181250005"),

  ENTIRE_LARGE_INTESTINE_BODY_STRUCTURE("Entire large intestine (body structure)", "", "SNOMED Clinical Terms", "181254001"),

  ENTIRE_LUNG_BODY_STRUCTURE("Entire lung (body structure)", "", "SNOMED Clinical Terms", "181216001"),

  ENTIRE_LIVER_BODY_STRUCTURE("Entire liver (body structure)", "", "SNOMED Clinical Terms", "181268008"),

  ENTIRE_PANCREAS_BODY_STRUCTURE("Entire pancreas (body structure)", "", "SNOMED Clinical Terms", "181277001"),

  TENDON_STRUCTURE_BODY_STRUCTURE("Tendon structure (body structure)", "", "SNOMED Clinical Terms", "13024002"),

  ENTIRE_HEART_VALVE_BODY_STRUCTURE("Entire heart valve (body structure)", "", "SNOMED Clinical Terms", "181285005"),

  ENTIRE_CORNEA_BODY_STRUCTURE("Entire cornea (body structure)", "", "SNOMED Clinical Terms", "181162001"),

  BLOOD_VESSEL_PART_BODY_STRUCTURE("Blood vessel part (body structure)", "", "SNOMED Clinical Terms", "119206002"),

  ENTIRE_KIDNEY_BODY_STRUCTURE("Entire kidney (body structure)", "", "SNOMED Clinical Terms", "181414000"),

  SKIN_PART_BODY_STRUCTURE("Skin part (body structure)", "", "SNOMED Clinical Terms", "119181002"),

  CARTILAGE_TISSUE_BODY_STRUCTURE("Cartilage tissue (body structure)", "", "SNOMED Clinical Terms", "309312004"),

  ENTIRE_HEART_BODY_STRUCTURE("Entire heart (body structure)", "", "SNOMED Clinical Terms", "302509004"),

  EAR_OSSICLE_STRUCTURE_BODY_STRUCTURE("Ear ossicle structure (body structure)", "", "SNOMED Clinical Terms", "41845008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDerKoerperstelleDefiningCode(String value, String description, String terminologyId,
      String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
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
