package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDerKoerperstelleDefiningCode implements EnumValueSet {
  STRUCTURE_OF_POSTERIOR_WALL_OF_OROPHARYNX_BODY_STRUCTURE("Structure of posterior wall of oropharynx (body structure)", "Structure of posterior wall of oropharynx (body structure)", "SNOMED CT", "12999009"),

  TRACHEAL_STRUCTURE_BODY_STRUCTURE("Tracheal structure (body structure)", "Tracheal structure (body structure)", "SNOMED CT", "44567001"),

  STRUCTURE_OF_INTERNAL_PART_OF_MOUTH_BODY_STRUCTURE("Structure of internal part of mouth (body structure)", "Structure of internal part of mouth (body structure)", "SNOMED CT", "700016008"),

  STRUCTURE_OF_POSTERIOR_NASOPHARYNX_BODY_STRUCTURE("Structure of posterior nasopharynx (body structure)", "Structure of posterior nasopharynx (body structure)", "SNOMED CT", "367592002"),

  PULMONARY_ALVEOLAR_STRUCTURE_BODY_STRUCTURE("Pulmonary alveolar structure (body structure)", "Pulmonary alveolar structure (body structure)", "SNOMED CT", "113253006"),

  BRONCHIAL_STRUCTURE_BODY_STRUCTURE("Bronchial structure (body structure)", "Bronchial structure (body structure)", "SNOMED CT", "955009"),

  TRACHEOBRONCHIAL_STRUCTURE_BODY_STRUCTURE("Tracheobronchial structure (body structure)", "Tracheobronchial structure (body structure)", "SNOMED CT", "91724006"),

  LOWER_RESPIRATORY_TRACT_STRUCTURE_BODY_STRUCTURE("Lower respiratory tract structure (body structure)", "Lower respiratory tract structure (body structure)", "SNOMED CT", "82094008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDerKoerperstelleDefiningCode(String value, String description, String terminologyId,
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