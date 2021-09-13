package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieDefiningCode implements EnumValueSet {
  PRIVACY_POLICY_ORGANIZATION_DOCUMENT("Privacy policy Organization Document", "", "LOINC", "57017-6"),

  PATIENT_CONSENT("Patient Consent", "", "LOINC", "59284-0"),

  RELEASE_OF_INFORMATION_CONSENT("Release of information consent", "", "LOINC", "64292-6"),

  PRIVACY_POLICY_ACKNOWLEDGEMENT_DOCUMENT("Privacy policy acknowledgement Document", "", "LOINC", "57016-8");

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
