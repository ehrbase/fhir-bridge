package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LocationOfMeasurementDefiningCode implements EnumValueSet {
  LEFT_THIGH("Left thigh", "The left thigh of the person.", "local", "at0028"),

  DORSUM_OF_FOOT("Dorsum of foot", "The individual's dorsum of the foot.", "local", "at1056"),

  RIGHT_ARM("Right arm", "The right arm of the person.", "local", "at0025"),

  LEFT_WRIST("Left wrist", "The left wrist of the individual.", "local", "at1021"),

  LEFT_ARM("Left arm", "The left arm of the person.", "local", "at0026"),

  RIGHT_THIGH("Right thigh", "The right thigh of the person.", "local", "at0027"),

  RIGHT_WRIST("Right wrist", "The right wrist of the individual.", "local", "at1020"),

  RIGHT_ANKLE("Right ankle", "The right ankle of the individual.", "local", "at1026"),

  TOE("Toe", "A toe of the individual.", "local", "at1051"),

  LEFT_ANKLE("Left ankle", "The left ankle of the individual.", "local", "at1031"),

  INTRA_ARTERIAL("Intra-arterial", "Invasive measurement via transducer access line within an artery.", "local", "at1053"),

  FINGER("Finger", "A finger of the individual.", "local", "at1032");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LocationOfMeasurementDefiningCode(String value, String description, String terminologyId,
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
