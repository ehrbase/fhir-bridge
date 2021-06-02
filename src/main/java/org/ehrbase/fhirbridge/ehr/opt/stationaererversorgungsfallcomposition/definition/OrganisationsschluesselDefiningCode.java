package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum OrganisationsschluesselDefiningCode implements EnumValueSet {
  NUKLEARMEDIZIN("Nuklearmedizin", "", "Anhang 1 der BPflV (31.12.2003)", "3200"),

  PADIATRIE("Padiatrie", "", "Anhang 1 der BPflV (31.12.2003)", "1000"),

  KINDERKARDIOLOGIE("Kinderkardiologie", "", "Anhang 1 der BPflV (31.12.2003)", "1100"),

  UROLOGIE("Urologie", "", "Anhang 1 der BPflV (31.12.2003)", "2200"),

  STRAHLENHEILKUNDE("Strahlenheilkunde", "", "Anhang 1 der BPflV (31.12.2003)", "3300"),

  GASTROENTEROLOGIE("Gastroenterologie", "", "Anhang 1 der BPflV (31.12.2003)", "0700"),

  ZAHN_UND_KIEFERHEILKUNDE_MUND_UND_KIEFERCHIRURGIE("Zahn- und Kieferheilkunde, Mund- und Kieferchirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "3500"),

  UNFALLCHIRURGIE("Unfallchirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "1600"),

  SONSTIGE_FACHABTEILUNG("Sonstige Fachabteilung", "", "Anhang 1 der BPflV (31.12.2003)", "3700"),

  GERIATRIE("Geriatrie", "", "Anhang 1 der BPflV (31.12.2003)", "0200"),

  ALLGEMEINE_PSYCHIATRIE("Allgemeine Psychiatrie", "", "Anhang 1 der BPflV (31.12.2003)", "2900"),

  HALS_NASEN_OHRENHEILKUNDE("Hals-, Nasen-, Ohrenheilkunde", "", "Anhang 1 der BPflV (31.12.2003)", "2600"),

  ORTHOPADIE_UND_UNFALLCHIRURGIE("Orthopadie und Unfallchirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "2316"),

  ALLGEMEINE_CHIRURGIE("Allgemeine Chirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "1500"),

  THORAXCHIRURGIE("Thoraxchirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "2000"),

  KINDER_UND_JUGENDPSYCHIATRIE("Kinder- und Jugendpsychiatrie", "", "Anhang 1 der BPflV (31.12.2003)", "3000"),

  NEONATOLOGIE("Neonatologie", "", "Anhang 1 der BPflV (31.12.2003)", "1200"),

  GEFA_CHIRURGIE("Gefa.chirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "1800"),

  NEPHROLOGIE("Nephrologie", "", "Anhang 1 der BPflV (31.12.2003)", "0400"),

  PSYCHOSOMATIK_PSYCHOTHERAPIE("Psychosomatik/Psychotherapie", "", "Anhang 1 der BPflV (31.12.2003)", "3100"),

  FRAUENHEILKUNDE("Frauenheilkunde", "", "Anhang 1 der BPflV (31.12.2003)", "2425"),

  ORTHOPADIE("Orthopadie", "", "Anhang 1 der BPflV (31.12.2003)", "2300"),

  PNEUMOLOGIE("Pneumologie", "", "Anhang 1 der BPflV (31.12.2003)", "0800"),

  NEUROLOGIE("Neurologie", "", "Anhang 1 der BPflV (31.12.2003)", "2800"),

  HAMATOLOGIE_UND_INTERNISTISCHE_ONKOLOGIE("Hamatologie und internistische Onkologie", "", "Anhang 1 der BPflV (31.12.2003)", "0500"),

  GEBURTSHILFE("Geburtshilfe", "", "Anhang 1 der BPflV (31.12.2003)", "2500"),

  INNERE_MEDIZIN("Innere Medizin", "", "Anhang 1 der BPflV (31.12.2003)", "0100"),

  KARDIOLOGIE("Kardiologie", "", "Anhang 1 der BPflV (31.12.2003)", "0300"),

  PLASTISCHE_CHIRURGIE("Plastische Chirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "1900"),

  KINDERCHIRURGIE("Kinderchirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "1300"),

  HERZCHIRURGIE("Herzchirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "2100"),

  ENDOKRINOLOGIE("Endokrinologie", "", "Anhang 1 der BPflV (31.12.2003)", "0600"),

  RHEUMATOLOGIE("Rheumatologie", "", "Anhang 1 der BPflV (31.12.2003)", "0900"),

  INTENSIVMEDIZIN("Intensivmedizin", "", "Anhang 1 der BPflV (31.12.2003)", "3600"),

  AUGENHEILKUNDE("Augenheilkunde", "", "Anhang 1 der BPflV (31.12.2003)", "2700"),

  NEUROCHIRURGIE("Neurochirurgie", "", "Anhang 1 der BPflV (31.12.2003)", "1700"),

  LUNGEN_UND_BRONCHIALHEILKUNDE("Lungen- und Bronchialheilkunde", "", "Anhang 1 der BPflV (31.12.2003)", "1400"),

  DERMATOLOGIE("Dermatologie", "", "Anhang 1 der BPflV (31.12.2003)", "3400"),

  FRAUENHEILKUNDE_UND_GEBURTSHILFE("Frauenheilkunde und Geburtshilfe", "", "Anhang 1 der BPflV (31.12.2003)", "2400");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  OrganisationsschluesselDefiningCode(String value, String description, String terminologyId,
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
