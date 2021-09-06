package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StateRegionDefiningCode implements EnumValueSet {
  HAMBURG("Hamburg", "", "urn:iso:std:iso:3166-2:de", "DE-HH"),

  BAYERN("Bayern", "", "urn:iso:std:iso:3166-2:de", "DE-BY"),

  BERLIN("Berlin", "", "urn:iso:std:iso:3166-2:de", "DE-BE"),

  HESSEN("Hessen", "", "urn:iso:std:iso:3166-2:de", "DE-HE"),

  BRANDENBURG("Brandenburg", "", "urn:iso:std:iso:3166-2:de", "DE-BB"),

  BREMEN("Bremen", "", "urn:iso:std:iso:3166-2:de", "DE-HB"),

  BADEN_WUERTTEMBERG("Baden-Württemberg", "", "urn:iso:std:iso:3166-2:de", "DE-BW"),

  MECKLENBURG_VORPOMMERN("Mecklenburg-Vorpommern", "", "urn:iso:std:iso:3166-2:de", "DE-MV"),

  SCHLESWIG_HOLSTEIN("Schleswig-Holstein", "", "urn:iso:std:iso:3166-2:de", "DE-SH"),

  RHEINLAND_PFALZ("Rheinland-Pfalz", "", "urn:iso:std:iso:3166-2:de", "DE-RP"),

  SAARLAND("Saarland", "", "urn:iso:std:iso:3166-2:de", "DE-SL"),

  SACHSEN_ANHALT("Sachsen-Anhalt", "", "urn:iso:std:iso:3166-2:de", "DE-ST"),

  THUERINGEN("Thüringen", "", "urn:iso:std:iso:3166-2:de", "DE-TH"),

  NORDRHEIN_WESTFALEN("Nordrhein-Westfalen", "", "urn:iso:std:iso:3166-2:de", "DE-NW"),

  SACHSEN("Sachsen", "", "urn:iso:std:iso:3166-2:de", "DE-SN"),

  NIEDERSACHSEN("Niedersachsen", "", "urn:iso:std:iso:3166-2:de", "DE-NI");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StateRegionDefiningCode(String value, String description, String terminologyId, String code) {
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
