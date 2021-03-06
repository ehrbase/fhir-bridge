package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:52:54.898513+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_CODED_TEXT")
public class ProblemDiagnoseSchweregradDvCodedText implements RMEntity, ProblemDiagnoseSchweregradChoice {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Schweregrad/Schweregrad
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
