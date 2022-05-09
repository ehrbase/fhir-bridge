package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BodySiteDefiningCode implements EnumValueSet {
  TOE("Toe", "An unspecified toe.", "local", "at1054"),

  EAR_LOBE("Ear lobe", "The lobe of an unspecified ear.", "local", "at1051"),

  RADIAL_ARTERY_RIGHT("Radial Artery - Right", "The right radial artery.", "local", "at1039"),

  CAROTID_ARTERY_RIGHT("Carotid Artery - Right", "The right carotid artery.", "local", "at1042"),

  BRACHIAL_ARTERY_LEFT("Brachial artery - Left", "The left brachial artery.", "local", "at1048"),

  FEMORAL_ARTERY_RIGHT("Femoral Artery - Right", "The right femoral artery.", "local", "at1044"),

  HEART("Heart", "The region of the heart.", "local", "at1040"),

  BRACHIAL_ARTERY_RIGHT("Brachial artery - Right", "The right brachial artery.", "local", "at1049"),

  FINGER("Finger", "An unspecified finger.", "local", "at1047"),

  FEMORAL_ARTERY_LEFT("Femoral Artery - Left", "The left femoral artery.", "local", "at1043"),

  CAROTID_ARTERY_LEFT("Carotid Artery - Left", "The left carotid artery.", "local", "at1041"),

  RADIAL_ARTERY_LEFT("Radial Artery - Left", "The left radial artery.", "local", "at1038");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BodySiteDefiningCode(String value, String description, String terminologyId, String code) {
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
