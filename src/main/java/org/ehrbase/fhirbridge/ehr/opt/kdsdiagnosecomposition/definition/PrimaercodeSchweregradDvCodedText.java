package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-22T15:55:37.620744279+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
@OptionFor("DV_CODED_TEXT")
public class PrimaercodeSchweregradDvCodedText implements RMEntity, PrimaercodeSchweregradChoice {
  /**
   * Path: Diagnose/Primärcode/Schweregrad/Schweregrad
   * Description: Eine Gesamtbeurteilung des Schweregrades des Problems oder der Diagnose.
   * Comment: Ist der Schweregrad über vordefinierte Codes im Element "Name des Problems/ der Diagnose" enthalten, wird dieses Datenelement überflüssig. Hinweis: Eine spezifischere Einstufung des Schweregrads kann mit Hilfe des SLOTs "Spezifische Angaben" angegeben werden.
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
