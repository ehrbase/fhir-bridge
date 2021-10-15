package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

import java.lang.Boolean;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T18:13:35.207946+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_BOOLEAN")
public class ProAnalytQuantitativesErgebnisDvBoolean implements RMEntity, ProAnalytQuantitativesErgebnisChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Quantitatives Ergebnis/Quantitatives Ergebnis
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("|value")
  private Boolean quantitativesErgebnisValue;

  public void setQuantitativesErgebnisValue(Boolean quantitativesErgebnisValue) {
     this.quantitativesErgebnisValue = quantitativesErgebnisValue;
  }

  public Boolean isQuantitativesErgebnisValue() {
     return this.quantitativesErgebnisValue ;
  }
}
