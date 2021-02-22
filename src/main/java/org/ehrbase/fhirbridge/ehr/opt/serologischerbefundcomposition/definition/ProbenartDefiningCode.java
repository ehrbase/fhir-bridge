package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ProbenartDefiningCode implements EnumValueSet {
  SPUTUM_SPECIMEN_SPECIMEN("Sputum specimen (specimen)", "Sputum specimen (specimen)", "SNOMED CT", "119334006"),

  SWAB_SPECIMEN("Swab (specimen)", "Swab (specimen)", "SNOMED CT", "257261003"),

  SALIVA_SPECIMEN_SPECIMEN("Saliva specimen (specimen)", "Saliva specimen (specimen)", "SNOMED CT", "119342007"),

  RESPIRATORY_FLUID_SPECIMEN_SPECIMEN("Respiratory fluid specimen (specimen)", "Respiratory fluid specimen (specimen)", "SNOMED CT", "410580001"),

  BRONCHOALVEOLAR_LAVAGE_FLUID_SAMPLE_SPECIMEN("Bronchoalveolar lavage fluid sample (specimen)", "Bronchoalveolar lavage fluid sample (specimen)", "SNOMED CT", "258607008");

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
