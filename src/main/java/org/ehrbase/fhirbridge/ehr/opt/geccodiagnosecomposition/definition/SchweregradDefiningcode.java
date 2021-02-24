package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradDefiningcode implements EnumValueSet {
  FATAL_QUALIFIER_VALUE("Fatal (qualifier value)", null, "SNOMED Clinical Terms", "399166001"),

  LIFE_THREATENING_SEVERITY_QUALIFIER_VALUE("Life threatening severity (qualifier value)", null, "SNOMED Clinical Terms", "442452003"),

  SEVERE_SEVERITY_MODIFIER_QUALIFIER_VALUE("Severe (severity modifier) (qualifier value)", null, "SNOMED Clinical Terms", "24484000"),

  UNKNOWN_QUALIFIER_VALUE("Unknown (qualifier value)", null, "SNOMED Clinical Terms", "261665006"),

  SCHWER("Schwer", "Das Problem oder die Diagnose verhindert die normale Aktivität oder verursacht schwerwiegende gesundheitliche Schäden, falls es nicht behandelt wird.", "SNOMED Clinical Terms", "at0049"),

  MILD_QUALIFIER_VALUE("Mild (qualifier value)", null, "SNOMED Clinical Terms", "255604002"),

  MASSIG("Mäßig", "Das Problem oder die Diagnose beeinträchtigt die normale Aktivität oder verursacht bleibende gesundheitliche Schäden, falls es nicht behandelt wird.", "SNOMED Clinical Terms", "at0048"),

  LEICHT("Leicht", "Das Problem oder die Diagnose beeinträchtigt die normale Aktivität nicht, bzw. verursacht nicht bleibende gesundheitliche Schäden, falls es nicht behandelt wird.", "SNOMED Clinical Terms", "at0047"),

  REMISSION_PHASE_QUALIFIER_VALUE("Remission phase (qualifier value)", null, "SNOMED Clinical Terms", "277022003"),

  MODERATE_TO_SEVERE_QUALIFIER_VALUE("Moderate to severe (qualifier value)", null, "SNOMED Clinical Terms", "371924009"),

  MODERATE_SEVERITY_MODIFIER_QUALIFIER_VALUE("Moderate (severity modifier) (qualifier value)", null, "SNOMED Clinical Terms", "6736007"),

  MILD_TO_MODERATE_QUALIFIER_VALUE("Mild to moderate (qualifier value)", null, "SNOMED Clinical Terms", "371923003");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SchweregradDefiningcode(String value, String description, String terminologyId, String code) {
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
