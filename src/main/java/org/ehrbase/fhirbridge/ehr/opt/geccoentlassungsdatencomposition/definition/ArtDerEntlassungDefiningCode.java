package org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDerEntlassungDefiningCode implements EnumValueSet {
  NICHT_BEKANNT("Nicht bekannt", "", "SNOMED CT", "261665006"),

  PALLIATIVE_ENTLASSUNG("Palliative Entlassung", "", "SNOMED CT", "306237005"),

  TOD("Tod", "", "SNOMED CT", "419099009"),

  KRANKENHAUSEINWEISUNG("Krankenhauseinweisung", "", "SNOMED CT", "32485007"),

  UEBERWEISUNG_IN_EINE_ANDERE_EINRICHTUNG("Überweisung in eine andere Einrichtung", "", "SNOMED CT", "3457005"),

  LEBEND_ENTLASSEN("Lebend entlassen", "", "SNOMED CT", "306237005");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDerEntlassungDefiningCode(String value, String description, String terminologyId,
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
