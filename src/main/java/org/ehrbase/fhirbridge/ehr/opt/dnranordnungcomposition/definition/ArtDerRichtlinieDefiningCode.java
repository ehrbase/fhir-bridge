package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDerRichtlinieDefiningCode implements EnumValueSet {
  DO_NO_RESUSCIATE("Do No Resusciate", "", "http://terminology.hl7.org/CodeSystem/consentcategorycodes", "dnr");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDerRichtlinieDefiningCode(String value, String description, String terminologyId,
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
