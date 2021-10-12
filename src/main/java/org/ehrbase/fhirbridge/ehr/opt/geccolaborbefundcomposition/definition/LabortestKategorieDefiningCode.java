package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LabortestKategorieDefiningCode implements EnumValueSet {
  MICROBIOLOGY_STUDIES_SET("Microbiology studies (set)", "", "http://loinc.org", "18725-2"),

  LABORATORY_STUDIES_SET("Laboratory studies (set)", "", "http://loinc.org", "26436-6"),

  CARDIAC_BIOMARKERS_SET("Cardiac biomarkers (set)", "", "http://loinc.org", "56846-9"),

  HEMATOLOGY_STUDIES_SET("Hematology studies (set)", "", "http://loinc.org", "18723-7"),

  BLOOD_GAS_STUDIES_SET("Blood gas studies (set)", "", "http://loinc.org", "18767-4"),

  SEROLOGY_AND_BLOOD_BANK_STUDIES_SET("Serology and blood bank studies (set)", "", "http://loinc.org", "56874-1"),

  CHEMISTRY_STUDIES_SET("Chemistry studies (set)", "", "http://loinc.org", "18719-5"),

  COAGULATION_STUDIES_SET("Coagulation studies (set)", "", "http://loinc.org", "18720-3"),

  URINALYSIS_STUDIES_SET("Urinalysis studies (set)", "", "http://loinc.org", "18729-4"),

  TOXICOLOGY_STUDIES_SET("Toxicology studies (set)", "", "http://loinc.org", "18728-6"),

  CALCULATED_AND_DERIVED_VALUES_SET("Calculated and derived values (set)", "", "http://loinc.org", "56847-7");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LabortestKategorieDefiningCode(String value, String description, String terminologyId,
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
