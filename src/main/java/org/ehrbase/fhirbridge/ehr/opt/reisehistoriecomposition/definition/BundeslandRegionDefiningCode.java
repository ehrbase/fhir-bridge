package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BundeslandRegionDefiningCode implements EnumValueSet {
  HESSEN("Hessen", "Hessen", "urn:iso:std:iso:3166-2:de", "DE-HE"),

  THUERINGEN("Thüringen", "Thüringen", "urn:iso:std:iso:3166-2:de", "DE-TH"),

  MECKLENBURG_VORPOMMERN("Mecklenburg-Vorpommern", "Mecklenburg-Vorpommern", "urn:iso:std:iso:3166-2:de", "DE-MV"),

  BAYERN("Bayern", "Bayern", "urn:iso:std:iso:3166-2:de", "DE-BY"),

  NIEDERSACHSEN("Niedersachsen", "Niedersachsen", "urn:iso:std:iso:3166-2:de", "DE-NI"),

  BADEN_WUERTTEMBERG("Baden-Württemberg", "Baden-Württemberg", "urn:iso:std:iso:3166-2:de", "DE-BW"),

  HAMBURG("Hamburg", "Hamburg", "urn:iso:std:iso:3166-2:de", "DE-HH"),

  SAARLAND("Saarland", "Saarland", "urn:iso:std:iso:3166-2:de", "DE-SL"),

  NORDRHEIN_WESTFALEN("Nordrhein-Westfalen", "Nordrhein-Westfalen", "urn:iso:std:iso:3166-2:de", "DE-NW"),

  BRANDENBURG("Brandenburg", "Brandenburg", "urn:iso:std:iso:3166-2:de", "DE-BB"),

  SACHSEN("Sachsen", "Sachsen", "urn:iso:std:iso:3166-2:de", "DE-SN"),

  SCHLESWIG_HOLSTEIN("Schleswig-Holstein", "Schleswig-Holstein", "urn:iso:std:iso:3166-2:de", "DE-SH"),

  RHEINLAND_PFALZ("Rheinland-Pfalz", "Rheinland-Pfalz", "urn:iso:std:iso:3166-2:de", "DE-RP"),

  SACHSEN_ANHALT("Sachsen-Anhalt", "Sachsen-Anhalt", "urn:iso:std:iso:3166-2:de", "DE-ST"),

  BREMEN("Bremen", "Bremen", "urn:iso:std:iso:3166-2:de", "DE-HB"),

  BERLIN("Berlin", "Berlin", "urn:iso:std:iso:3166-2:de", "DE-BE");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BundeslandRegionDefiningCode(String value, String description, String terminologyId,
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
