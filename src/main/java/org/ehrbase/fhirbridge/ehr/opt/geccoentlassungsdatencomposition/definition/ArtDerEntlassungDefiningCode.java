package org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDerEntlassungDefiningCode implements EnumValueSet {
  DEAD_FINDING("Dead (finding)", "", "SNOMED-CT", "419099009"),

  UNKNOWN_QUALIFIER_VALUE("Unknown (qualifier value)", "", "SNOMED-CT", "261665006"),

  PATIENT_DISCHARGED_ALIVE_FINDING("Patient discharged alive (finding)", "", "SNOMED-CT", "371827001"),

  PATIENT_REFERRAL_PROCEDURE("Patient referral (procedure)", "", "SNOMED-CT", "3457005"),

  REFERRAL_TO_PALLIATIVE_CARE_SERVICE_PROCEDURE("Referral to palliative care service (procedure)", "", "SNOMED-CT", "306237005"),

  HOSPITAL_ADMISSION_PROCEDURE("Hospital admission (procedure)", "", "SNOMED-CT", "32485007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDerEntlassungDefiningCode(String value, String description, String terminologyId,
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
