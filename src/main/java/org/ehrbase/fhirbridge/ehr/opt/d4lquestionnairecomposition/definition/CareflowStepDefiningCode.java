package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CareflowStepDefiningCode implements EnumValueSet {
  VERSCHOBEN("Verschoben", "Die Absicht, eine Einwilligungserklärung anzufordern, wurde verschoben.", "openehr", "at0019"),

  EINWILLIGUNG_ERTEILT("Einwilligung erteilt", "Die Einwilligungserklärung wurde vom Patienten oder seinem Vertreter erteilt.", "openehr", "at0015"),

  TERMIN_GEPLANT("Termin geplant", "Es wurde ein Termin vereinbart, um die Zustimmung einzuholen.", "openehr", "at0027"),

  EINWILLIGUNG_ERBETEN("Einwilligung erbeten", "Die Einwilligungserklärung wurde vom Patienten oder seinem Vertreter angefragt, es ist jedoch keine Antwort eingegangen.", "openehr", "at0014"),

  EINWILLIGUNG_NICHT_ERHALTEN("Einwilligung nicht erhalten", "Eine Einwilligungserklärung war weder vom Patienten noch vom Vertreter des Patienten erhältlich.", "openehr", "at0021"),

  EINWILLIGUNG_WIDERRUFEN("Einwilligung widerrufen", "Nach der ersten Übermittlung einer Einwilligungserklärung wurde diese vom Patienten oder seinem Vertreter widerrufen.", "openehr", "at0017"),

  GEPLANT("Geplant", "Die Notwendigkeit einer Einwilligungserklärung wird festgestellt.", "openehr", "at0013"),

  ABGESCHLOSSEN("Abgeschlossen", "Der Patient oder sein Vertreter hat eine Einwilligungserklärung abgegeben und die Aktivität ist nun abgeschlossen.", "openehr", "at0022"),

  EINWILLIGUNG_VERWEIGERT("Einwilligung verweigert", "Als Antwort auf eine Anfrage zur Einwilligungserklärung, wurde sie vom Patienten oder seinem Vertreter abgelehnt.", "openehr", "at0016"),

  ABGESAGT("Abgesagt", "Die Absicht, eine Einwilligungserklärung anzufordern, wurde aufgehoben, bevor die Einwilligung des Patienten oder seines Vertreters angefordert wurde.", "openehr", "at0018");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  CareflowStepDefiningCode(String value, String description, String terminologyId, String code) {
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
