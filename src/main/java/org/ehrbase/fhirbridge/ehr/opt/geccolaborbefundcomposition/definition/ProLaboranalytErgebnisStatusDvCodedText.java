package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-05T14:12:01.337115+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
@OptionFor("DV_CODED_TEXT")
public class ProLaboranalytErgebnisStatusDvCodedText implements RMEntity, ProLaboranalytErgebnisStatusChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Ergebnis-Status/Ergebnis-Status
   * Description: Status des Analyt-Ergebniswertes.
   */
  @Path("|defining_code")
  private ErgebnisStatusDefiningCode ergebnisStatusDefiningCode;

  public void setErgebnisStatusDefiningCode(ErgebnisStatusDefiningCode ergebnisStatusDefiningCode) {
     this.ergebnisStatusDefiningCode = ergebnisStatusDefiningCode;
  }

  public ErgebnisStatusDefiningCode getErgebnisStatusDefiningCode() {
     return this.ergebnisStatusDefiningCode ;
  }
}
