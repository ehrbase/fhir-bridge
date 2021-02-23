package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BerufsbereichDefiningCode implements EnumValueSet {
  GEMEINSCHAFTSEINRICHTUNG_SCHULE_KITA_UNIVERSITAET_HEIM_ETC("Gemeinschaftseinrichtung (Schule, Kita, Universität, Heim etc.)", "Gemeinschaftseinrichtung (Schule, Kita, Universität, Heim etc.)", "http://fhir.data4life.care/covid-19/r4/CodeSystem/occupation-class", "community"),

  MEDIZINISCHEN_BEREICH_PFLEGE_ARZTPRAXIS_ODER_KRANKENHAUS("Medizinischen Bereich (Pflege, Arztpraxis oder Krankenhaus)", "Medizinischen Bereich (Pflege, Arztpraxis oder Krankenhaus)", "http://fhir.data4life.care/covid-19/r4/CodeSystem/occupation-class", "medical"),

  SONSTIGES("Sonstiges", "Sonstiges", "http://fhir.data4life.care/covid-19/r4/CodeSystem/occupation-class", "LA46-8");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BerufsbereichDefiningCode(String value, String description, String terminologyId, String code) {
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
