package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.Double;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-20T15:29:31.657782+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_QUANTITY")
public class ProLaboranalytMesswertDvQuantity implements RMEntity, ProLaboranalytMesswertChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("|magnitude")
  private Double messwertMagnitude;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("|units")
  private String messwertUnits;

  public void setMesswertMagnitude(Double messwertMagnitude) {
     this.messwertMagnitude = messwertMagnitude;
  }

  public Double getMesswertMagnitude() {
     return this.messwertMagnitude ;
  }

  public void setMesswertUnits(String messwertUnits) {
     this.messwertUnits = messwertUnits;
  }

  public String getMesswertUnits() {
     return this.messwertUnits ;
  }
}
