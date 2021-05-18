package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TitelDerStudiePruefungDefiningCode implements EnumValueSet {
  PARTICIPATION_IN_INTERVENTIONAL_CLINICAL_TRIALS("Participation in interventional clinical trials", "", "eCRF", "03");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  TitelDerStudiePruefungDefiningCode(String value, String description, String terminologyId,
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
