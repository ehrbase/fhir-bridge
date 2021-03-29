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
    date = "2021-03-09T11:53:24.167613+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_QUANTITY")
public class ProLaboranalytMesswertDvQuantity implements RMEntity, ProLaboranalytMesswertChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("|magnitude")
  private Double messwertMagnitude;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Resultats.
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
