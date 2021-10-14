package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

import java.time.temporal.Temporal;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T18:13:35.215052+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_DATE")
public class ProAnalytQuantitativesErgebnisDvDate implements RMEntity, ProAnalytQuantitativesErgebnisChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Quantitatives Ergebnis/Quantitatives Ergebnis
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("|value")
  private Temporal quantitativesErgebnisValue;

  public void setQuantitativesErgebnisValue(Temporal quantitativesErgebnisValue) {
     this.quantitativesErgebnisValue = quantitativesErgebnisValue;
  }

  public Temporal getQuantitativesErgebnisValue() {
     return this.quantitativesErgebnisValue ;
  }
}
