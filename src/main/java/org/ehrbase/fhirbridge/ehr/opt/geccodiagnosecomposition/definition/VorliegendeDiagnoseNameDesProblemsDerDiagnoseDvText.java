package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-13T14:55:50.869917+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class VorliegendeDiagnoseNameDesProblemsDerDiagnoseDvText implements RMEntity, VorliegendeDiagnoseNameDesProblemsDerDiagnoseChoice {
  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Name des Problems/ der Diagnose/Name des Problems/ der Diagnose
   * Description: Namentliche Identifikation des Problems oder der Diagnose.
   */
  @Path("|value")
  private String nameDesProblemsDerDiagnoseValue;

  public void setNameDesProblemsDerDiagnoseValue(String nameDesProblemsDerDiagnoseValue) {
     this.nameDesProblemsDerDiagnoseValue = nameDesProblemsDerDiagnoseValue;
  }

  public String getNameDesProblemsDerDiagnoseValue() {
     return this.nameDesProblemsDerDiagnoseValue ;
  }
}
