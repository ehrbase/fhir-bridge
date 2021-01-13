package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradDefiningCode implements EnumValueSet {
  MODERAT("Moderat", "Die Intensität des Symptoms/Krankheitsanzeichens führt zu einer Beeinträchtigung der normalen Aktivität.", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "at0024"),

  N38_C("38 °C", "38 °C", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "38C"),

  N39_C("39 °C", "39 °C", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "39C"),

  N41_C("41 °C", "41 °C", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "41C"),

  N42_C("42 °C", "42 °C", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "42C"),

  ICH_WEISS_ES_NICHT("Ich weiß es nicht", "Ich weiß es nicht", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "LA12688-0"),

  LEICHT("Leicht", "Die Intensität des Symptoms/Krankheitsanzeichens führt zu keiner Beeinträchtigung der normalen Aktivität.", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "at0023"),

  SCHWER("Schwer", "Die Intensität des Symptoms/Krankheitsanzeichens verhindert eine normale Aktivität.", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "at0025"),

  N40_C("40 °C", "40 °C", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "40C");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SchweregradDefiningCode(String value, String description, String terminologyId, String code) {
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
