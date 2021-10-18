package org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum WirdWurdeDieAktivitaetDurchgefuehrtDefiningCode implements EnumValueSet {
  OTHER_QUALIFIER_VALUE("Other (qualifier value)", "", "SNOMED Clinical Terms", "74964007"),

  NO_QUALIFIER_VALUE("No (qualifier value)", "", "SNOMED Clinical Terms", "373067005"),

  UNKNOWN_QUALIFIER_VALUE("Unknown (qualifier value)", "", "SNOMED Clinical Terms", "261665006"),

  YES_QUALIFIER_VALUE("Yes (qualifier value)", "", "SNOMED Clinical Terms", "373066001"),

  NOT_APPLICABLE_QUALIFIER_VALUE("Not applicable (qualifier value)", "", "SNOMED Clinical Terms", "385432009");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  WirdWurdeDieAktivitaetDurchgefuehrtDefiningCode(String value, String description,
      String terminologyId, String code) {
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
