package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

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
    date = "2021-10-11T18:13:35.211414+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_QUANTITY")
public class ProAnalytQuantitativesErgebnisDvQuantity implements RMEntity, ProAnalytQuantitativesErgebnisChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Quantitatives Ergebnis/Quantitatives Ergebnis
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("|magnitude")
  private Double quantitativesErgebnisMagnitude;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Quantitatives Ergebnis/Quantitatives Ergebnis
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("|units")
  private String quantitativesErgebnisUnits;

  public void setQuantitativesErgebnisMagnitude(Double quantitativesErgebnisMagnitude) {
     this.quantitativesErgebnisMagnitude = quantitativesErgebnisMagnitude;
  }

  public Double getQuantitativesErgebnisMagnitude() {
     return this.quantitativesErgebnisMagnitude ;
  }

  public void setQuantitativesErgebnisUnits(String quantitativesErgebnisUnits) {
     this.quantitativesErgebnisUnits = quantitativesErgebnisUnits;
  }

  public String getQuantitativesErgebnisUnits() {
     return this.quantitativesErgebnisUnits ;
  }
}
