package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DiagnoserolleDefiningCode implements EnumValueSet {
  NEBENDIAGNOSE("Nebendiagnose", "Ein Problem oder eine Diagnose, die zur gleichen Zeit auftritt wie die Hauptdiagnose. Könnte auch als komorbider Zustand bezeichnet werden.", "local", "at0066"),

  KOMPLIKATION("Komplikation", "Eine ungünstige Entwicklung eines Problems oder einer Diagnose.", "local", "at0076"),

  HAUPTDIAGNOSE("Hauptdiagnose", "Die Diagnose wurde als Hauptgrund für eine Episode der zugewiesenen Patientenversorgung, eine Episode der stationären Pflege oder einen Besuch in der Einrichtung des Gesundheitswesens ermittelt.", "local", "at0064");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  DiagnoserolleDefiningCode(String value, String description, String terminologyId, String code) {
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
