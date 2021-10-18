package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-18T14:30:47.976815+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_CODED_TEXT")
public class VorliegendeDiagnoseSchweregradDvCodedText implements RMEntity, VorliegendeDiagnoseSchweregradChoice {
  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Schweregrad/Schweregrad
   * Description: Eine Gesamtbeurteilung des Schweregrades des Problems oder der Diagnose.
   */
  @Path("|defining_code")
  private SchweregradDefiningCode schweregradDefiningCode;

  public void setSchweregradDefiningCode(SchweregradDefiningCode schweregradDefiningCode) {
     this.schweregradDefiningCode = schweregradDefiningCode;
  }

  public SchweregradDefiningCode getSchweregradDefiningCode() {
     return this.schweregradDefiningCode ;
  }
}
