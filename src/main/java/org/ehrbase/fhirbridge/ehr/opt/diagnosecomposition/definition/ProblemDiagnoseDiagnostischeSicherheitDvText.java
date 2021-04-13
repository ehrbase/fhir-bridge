package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:52:54.896371+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_TEXT")
public class ProblemDiagnoseDiagnostischeSicherheitDvText implements RMEntity, ProblemDiagnoseDiagnostischeSicherheitChoice {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnostische Sicherheit/Diagnostische Sicherheit
   * Description: Grad der Sicherheit, mit der die Diagnose festgestellt wurde.
   */
  @Path("|value")
  private String diagnostischeSicherheitValue;

  public void setDiagnostischeSicherheitValue(String diagnostischeSicherheitValue) {
     this.diagnostischeSicherheitValue = diagnostischeSicherheitValue;
  }

  public String getDiagnostischeSicherheitValue() {
     return this.diagnostischeSicherheitValue ;
  }
}
