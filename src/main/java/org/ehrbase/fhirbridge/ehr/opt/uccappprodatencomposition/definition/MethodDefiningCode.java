package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MethodDefiningCode implements EnumValueSet {
  AUTOMATIC_NON_INVASIVE("Automatic, non-invasive", "The findings are observed non-invasively using a device such as a pulse oximeter or a stethoscope.", "local", "at1034"),

  AUSCULTATION("Auscultation", "The findings are observed with the assistance of a device, such as a stethoscope.", "local", "at1033"),

  PALPATION("Palpation", "The findings are observed by physical touch of the observer on the subject.", "local", "at1032");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MethodDefiningCode(String value, String description, String terminologyId, String code) {
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
