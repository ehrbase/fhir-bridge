package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:08.594201+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_CODED_TEXT")
public class KoerpertemperaturLokalisationDerMessungDvCodedText implements RMEntity, KoerpertemperaturLokalisationDerMessungChoice {
  /**
   * Path: Bericht/Körpertemperatur/Lokalisation der Messung/Lokalisation der Messung
   * Description: Der anatomische Lokalisation der Temperaturmessung.
   */
  @Path("|defining_code")
  private LokalisationDerMessungDefiningCode lokalisationDerMessungDefiningCode;

  public void setLokalisationDerMessungDefiningCode(
      LokalisationDerMessungDefiningCode lokalisationDerMessungDefiningCode) {
     this.lokalisationDerMessungDefiningCode = lokalisationDerMessungDefiningCode;
  }

  public LokalisationDerMessungDefiningCode getLokalisationDerMessungDefiningCode() {
     return this.lokalisationDerMessungDefiningCode ;
  }
}
