package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import java.lang.String;
import java.util.List;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;

public enum BeschreibungDefiningCode implements EnumValueSet {
  UNKNOWN_QUALIFIER_VALUE("Unknown (qualifier value)", "", "SNOMED Clinical Terms", "261665006"),

  FOR_RESUSCITATION_FINDING("For resuscitation (finding)", "", "SNOMED Clinical Terms", "304252001"),

  NOT_FOR_RESUSCITATION_FINDING("Not for resuscitation (finding)", "", "SNOMED Clinical Terms", "304253006");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BeschreibungDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

    public static BeschreibungDefiningCode get_by_SNOMED_code(List<CodeableConcept> code) {
      CodeableConcept fhir_code = code.get(0);
      for(BeschreibungDefiningCode bc : values()) {
        if(bc.code.equals(fhir_code.getCoding().get(0).getCode())) {
          return bc;
        }
      }
      throw new UnprocessableEntityException("Getting BeschreibungDefiningCode failed. Code not found for: " + fhir_code.getCoding().get(0).toString());
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
