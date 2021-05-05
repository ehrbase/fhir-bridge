package org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VirusnachweistestDefiningCode implements EnumValueSet {
  SARS_COV2_COVID19_RNA_PRESENCE_IN_RESPIRATORY_SPECIMEN_BY_NAA_WITH_PROBE_DETECTION("SARS-CoV-2 (COVID-19) RNA [Presence] in Respiratory specimen by NAA with probe detection", "", "LOINC", "94500-6");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VirusnachweistestDefiningCode(String value, String description, String terminologyId,
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
