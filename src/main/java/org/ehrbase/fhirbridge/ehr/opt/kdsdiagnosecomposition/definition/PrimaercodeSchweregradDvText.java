package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-22T15:55:37.621401521+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
@OptionFor("DV_TEXT")
public class PrimaercodeSchweregradDvText implements RMEntity, PrimaercodeSchweregradChoice {
  /**
   * Path: Diagnose/Primärcode/Schweregrad/Schweregrad
   * Description: Eine Gesamtbeurteilung des Schweregrades des Problems oder der Diagnose.
   * Comment: Ist der Schweregrad über vordefinierte Codes im Element "Name des Problems/ der Diagnose" enthalten, wird dieses Datenelement überflüssig. Hinweis: Eine spezifischere Einstufung des Schweregrads kann mit Hilfe des SLOTs "Spezifische Angaben" angegeben werden.
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
