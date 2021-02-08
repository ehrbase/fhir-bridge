package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum EthnischerHintergrundDefiningCode implements EnumValueSet {
  CAUCASIAN("Caucasian", "Caucasian", "SNOMED Clinical Terms", "14045001"),

  OTHER_ETHNIC_MIXED_ORIGIN("Other ethnic, mixed origin", "Other ethnic, mixed origin", "SNOMED Clinical Terms", "186019001"),

  ASIAN("Asian", "Asian", "SNOMED Clinical Terms", "315280000"),

  ARABS("Arabs", "Arabs", "SNOMED Clinical Terms", "90027003"),

  BLACK_AFRICAN("Black African", "Black African", "SNOMED Clinical Terms", "18167009"),

  HISPANIC_OR_LATINO("Hispanic or Latino", "Hispanic or Latino", "SNOMED Clinical Terms", "2135-2");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  EthnischerHintergrundDefiningCode(String value, String description, String terminologyId,
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
