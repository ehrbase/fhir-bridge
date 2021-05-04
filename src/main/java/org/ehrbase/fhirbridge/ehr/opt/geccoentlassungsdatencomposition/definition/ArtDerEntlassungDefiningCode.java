package org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;

public enum ArtDerEntlassungDefiningCode implements EnumValueSet {
  UNKNOWN_QUALIFIER_VALUE("Unknown (qualifier value)", "", "SNOMED-CT", "261665006"),
  HOSPITAL_ADMISSION_PROCEDURE("Hospital admission (procedure)","","SNOMED-CT","32485007"),
  DEAD_FINDING("Dead (finding)","", "SNOMED-CT", "419099009"),
  PATIENT_DISCHARGED_ALIVE_FINDING("Patient discharged alive (finding)", "", "SNOMED-CT", "371827001"),
  PATIENT_REFERRAL_PROCEDURE("Patient referral (procedure)", "", "SNOMED-CT", "3457005"),
  REFERRAL_TO_PALLIATIVE_CARE_SERVICE_PROCEDURE("Referral to palliative care service (procedure)", "", "SNOMED-CT", "306237005");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDerEntlassungDefiningCode(String value, String description, String terminologyId, String code) {
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


  public DvCodedText toDvCodedText(){
    DvCodedText dvCodedText = new DvCodedText();
    CodePhrase codePhrase = new CodePhrase();
    codePhrase.setCodeString(code);
    codePhrase.setTerminologyId(new TerminologyId(terminologyId));
    dvCodedText.setDefiningCode(codePhrase);
    dvCodedText.setValue(value);
    return dvCodedText;
  }

}