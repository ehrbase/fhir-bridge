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
    date = "2021-10-20T15:29:31.657265+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class ProLaboranalytMesswertDvText implements RMEntity, ProLaboranalytMesswertChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("|value")
  private String messwertValue;

  public void setMesswertValue(String messwertValue) {
     this.messwertValue = messwertValue;
  }

  public String getMesswertValue() {
     return this.messwertValue ;
  }
}
