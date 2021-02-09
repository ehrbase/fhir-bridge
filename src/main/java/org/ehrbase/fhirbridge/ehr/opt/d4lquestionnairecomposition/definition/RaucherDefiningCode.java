package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RaucherDefiningCode implements EnumValueSet {
  DERZEITIGER_RAUCHER("Derzeitiger Raucher", "Die Person ist derzeitiger Tabakraucher.", "LOINC", "at0003"),

  NIEMALS_GERAUCHT("Niemals geraucht", "Die Person hat niemals Tabak geraucht.", "LOINC", "at0006"),

  NEIN("Nein", "Nein", "LOINC", "LA32-8"),

  JA("Ja", "Ja", "LOINC", "LA33-6"),

  EHEMALIGER_RAUCHER("Ehemaliger Raucher", "Die Person hat früher Tabak geraucht, raucht derzeitig aber keinen Tabak.", "LOINC", "at0005");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RaucherDefiningCode(String value, String description, String terminologyId, String code) {
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
