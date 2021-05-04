package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieDefiningCode implements EnumValueSet {
  COMMUNITY("Community", "", "HL7 FHIR", "community"),

  OUTPATIENT("Outpatient", "", "HL7 FHIR", "outpatient"),

  PATIENT_SPECIFIED("Patient Specified", "", "HL7 FHIR", "patientspecified"),

  INPATIENT("Inpatient", "", "HL7 FHIR", "inpatient");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KategorieDefiningCode(String value, String description, String terminologyId, String code) {
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
