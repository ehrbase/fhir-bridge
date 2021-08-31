package org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode implements EnumValueSet {
  FINAL("final", "The observation is complete and there are no further actions needed. Additional information such \"released\", \"signed\", etc would be represented using [Provenance](provenance.html) which provides not only the act but also the actors and dates and other related data. These act states would be associated with an observation status of `preliminary` until they are all completed and then a status of `final` would be applied.", "local", "at0012"),

  PRELIMINARY("preliminary", "This is an initial or interim observation: data may be incomplete or unverified.", "local", "at0011"),

  AMENDED("amended", "Subsequent to being Final, the observation has been modified subsequent. This includes updates/new information and corrections.", "local", "at0013"),

  REGISTERED("registered", "The existence of the observation is registered, but there is no result yet available.", "local", "at0010");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode(String value, String description, String terminologyId, String code) {
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
