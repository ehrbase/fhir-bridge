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
    date = "2021-03-09T11:53:24.176621+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_TEXT")
public class LaborergebnisIdentifikationDerLaboranforderungDvText implements RMEntity, LaborergebnisIdentifikationDerLaboranforderungChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Identifikation der Laboranforderung/Identifikation der Laboranforderung
   * Description: Lokale Auftrags-ID des anfordernden/einsendenden Systems.
   */
  @Path("|value")
  private String identifikationDerLaboranforderungValue;

  public void setIdentifikationDerLaboranforderungValue(
      String identifikationDerLaboranforderungValue) {
     this.identifikationDerLaboranforderungValue = identifikationDerLaboranforderungValue;
  }

  public String getIdentifikationDerLaboranforderungValue() {
     return this.identifikationDerLaboranforderungValue ;
  }
}
