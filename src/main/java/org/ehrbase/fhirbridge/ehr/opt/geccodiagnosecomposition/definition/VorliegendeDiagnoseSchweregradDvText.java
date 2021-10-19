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
    date = "2021-10-19T15:40:13.517679+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class VorliegendeDiagnoseSchweregradDvText implements RMEntity, VorliegendeDiagnoseSchweregradChoice {
  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Schweregrad/Schweregrad
   * Description: Eine Gesamtbeurteilung des Schweregrades des Problems oder der Diagnose.
   */
  @Path("|value")
  private String schweregradValue;

  public void setSchweregradValue(String schweregradValue) {
     this.schweregradValue = schweregradValue;
  }

  public String getSchweregradValue() {
     return this.schweregradValue ;
  }
}
