package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UnregelmaessigerTypDefiningCode implements EnumValueSet {
  NORMAL_UNREGELMAESSIG("Normal unregelmäßig", "Der Verlauf ist unregelmäßig, aber in einem regelmäßigen Muster. Zum Beispiel das Ausfallen eines Schlages alle \"n\" Schläge.", "local", "at0007"),

  UNGLEICHMAESSIG_UNREGELMAESSIG("Ungleichmäßig unregelmäßig", "Der Verlauf ist auf chaotische und unvorhersehbare Weise unregelmäßig. Zum Beispiel: Vorhofflimmern.", "local", "at0008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UnregelmaessigerTypDefiningCode(String value, String description, String terminologyId,
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
