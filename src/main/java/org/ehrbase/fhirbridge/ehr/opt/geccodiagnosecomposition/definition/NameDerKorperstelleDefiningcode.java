package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDerKorperstelleDefiningcode implements EnumValueSet {
  N181254001("181254001", "181254001", "SNOMED Clinical Terms", "181254001"),

  N181285005("181285005", "181285005", "SNOMED Clinical Terms", "181285005"),

  N8935007("8935007", "8935007", "SNOMED Clinical Terms", "8935007"),

  N119206002("119206002", "119206002", "SNOMED Clinical Terms", "119206002"),

  N181268008("181268008", "181268008", "SNOMED Clinical Terms", "181268008"),

  N181250005("181250005", "181250005", "SNOMED Clinical Terms", "181250005"),

  N181216001("181216001", "181216001", "SNOMED Clinical Terms", "181216001"),

  N181277001("181277001", "181277001", "SNOMED Clinical Terms", "181277001"),

  N302509004("302509004", "302509004", "SNOMED Clinical Terms", "302509004"),

  N119181002("119181002", "119181002", "SNOMED Clinical Terms", "119181002"),

  N13024002("13024002", "13024002", "SNOMED Clinical Terms", "13024002"),

  N3138006("3138006", "3138006", "SNOMED Clinical Terms", "3138006"),

  N181414000("181414000", "181414000", "SNOMED Clinical Terms", "181414000"),

  N309312004("309312004", "309312004", "SNOMED Clinical Terms", "309312004"),

  N181162001("181162001", "181162001", "SNOMED Clinical Terms", "181162001"),

  N41845008("41845008", "41845008", "SNOMED Clinical Terms", "41845008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDerKorperstelleDefiningcode(String value, String description, String terminologyId,
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
