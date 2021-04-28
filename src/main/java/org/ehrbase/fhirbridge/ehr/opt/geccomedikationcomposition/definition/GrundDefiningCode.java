package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum GrundDefiningCode implements EnumValueSet {
  CURATIVE_PROCEDURE_INTENT_QUALIFIER_VALUE("Curative - procedure intent (qualifier value)", "", "SNOMED CT", "373808002"),

  ADJUNCT_INTENT_QUALIFIER_VALUE("Adjunct - intent (qualifier value)", "", "SNOMED CT", "421974008"),

  NEO_ADJUVANT_INTENT_QUALIFIER_VALUE("Neo-adjuvant - intent (qualifier value)", "", "SNOMED CT", "373847000"),

  SUPPORTIVE_PROCEDURE_INTENT_QUALIFIER_VALUE("Supportive - procedure intent (qualifier value)", "", "SNOMED CT", "399707004"),

  ADJUVANT_INTENT_QUALIFIER_VALUE("Adjuvant - intent (qualifier value)", "", "SNOMED CT", "373846009"),

  PROPHYLAXIS_PROCEDURE_INTENT_QUALIFIER_VALUE("Prophylaxis - procedure intent (qualifier value)", "", "SNOMED CT", "360271000");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  GrundDefiningCode(String value, String description, String terminologyId, String code) {
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
