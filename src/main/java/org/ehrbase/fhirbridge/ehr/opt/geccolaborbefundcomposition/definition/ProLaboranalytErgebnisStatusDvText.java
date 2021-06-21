package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-06-21T14:47:54.292526+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
@OptionFor("DV_TEXT")
public class ProLaboranalytErgebnisStatusDvText implements RMEntity, ProLaboranalytErgebnisStatusChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Ergebnis-Status/Ergebnis-Status
   * Description: Status des Analyseergebnisses.
   */
  @Path("|value")
  private String ergebnisStatusValue;

  public void setErgebnisStatusValue(String ergebnisStatusValue) {
     this.ergebnisStatusValue = ergebnisStatusValue;
  }

  public String getErgebnisStatusValue() {
     return this.ergebnisStatusValue ;
  }
}
