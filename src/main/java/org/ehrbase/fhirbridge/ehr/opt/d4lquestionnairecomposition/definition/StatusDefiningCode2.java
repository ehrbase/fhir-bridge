package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode2 implements EnumValueSet {
  ICH_WEISS_ES_NICHT("Ich weiß es nicht", "Ich weiß es nicht", "LOINC", "LA12688-0"),

  NEIN("Nein", "Nein", "LOINC", "LA32-8"),

  JA("Ja", "Ja", "LOINC", "LA33-6"),

  ZURZEIT_VERWENDET("Zurzeit verwendet", "Die Person verwendet derzeit das Medikament oder die Medikamentenklasse.", "LOINC", "at0025"),

  IN_DER_VERGANGENHEIT_VERWENDET("In der Vergangenheit verwendet", "Die Person hat das Medikament oder die Medikamentenklasse in der Vergangenheit verwendet, aber verwendet es zurzeit nicht.", "LOINC", "at0026"),

  NIE_VERWENDET("Nie verwendet", "Die Person hat das Medikament oder die Medikamentenklasse noch nie verwendet.", "LOINC", "at0024");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode2(String value, String description, String terminologyId, String code) {
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
