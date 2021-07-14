package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-12T18:58:20.592197+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
@OptionFor("DV_CODED_TEXT")
public class VorliegendeDiagnoseNameDesProblemsDerDiagnoseDvCodedText implements RMEntity, VorliegendeDiagnoseNameDesProblemsDerDiagnoseChoice {
  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Name des Problems/ der Diagnose/Name des Problems/ der Diagnose
   * Description: Namentliche Identifikation des Problems oder der Diagnose.
   */
  @Path("")
  private DvCodedText nameDesProblemsDerDiagnose;

  public void setNameDesProblemsDerDiagnose(DvCodedText nameDesProblemsDerDiagnose) {
     this.nameDesProblemsDerDiagnose = nameDesProblemsDerDiagnose;
  }

  public DvCodedText getNameDesProblemsDerDiagnose() {
     return this.nameDesProblemsDerDiagnose ;
  }
}
