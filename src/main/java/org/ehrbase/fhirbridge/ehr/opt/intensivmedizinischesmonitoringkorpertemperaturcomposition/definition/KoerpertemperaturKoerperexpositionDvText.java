package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:08.625898+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_TEXT")
public class KoerpertemperaturKoerperexpositionDvText implements RMEntity, KoerpertemperaturKoerperexpositionChoice {
  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Körperexposition/Körperexposition
   * Description: Die thermale Situation der Person, deren Temperatur gemessen wird.
   */
  @Path("|value")
  private String koerperexpositionValue;

  public void setKoerperexpositionValue(String koerperexpositionValue) {
     this.koerperexpositionValue = koerperexpositionValue;
  }

  public String getKoerperexpositionValue() {
     return this.koerperexpositionValue ;
  }
}
