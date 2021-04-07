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
    date = "2021-03-09T11:54:08.599997+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_TEXT")
public class KoerpertemperaturLokalisationDerMessungDvText implements RMEntity, KoerpertemperaturLokalisationDerMessungChoice {
  /**
   * Path: Bericht/Körpertemperatur/Lokalisation der Messung/Lokalisation der Messung
   * Description: Der anatomische Lokalisation der Temperaturmessung.
   */
  @Path("|value")
  private String lokalisationDerMessungValue;

  public void setLokalisationDerMessungValue(String lokalisationDerMessungValue) {
     this.lokalisationDerMessungValue = lokalisationDerMessungValue;
  }

  public String getLokalisationDerMessungValue() {
     return this.lokalisationDerMessungValue ;
  }
}
