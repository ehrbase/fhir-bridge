package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:08.624372+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_CODED_TEXT")
public class KoerpertemperaturKoerperexpositionDvCodedText implements RMEntity, KoerpertemperaturKoerperexpositionChoice {
  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Körperexposition/Körperexposition
   * Description: Die thermale Situation der Person, deren Temperatur gemessen wird.
   */
  @Path("|defining_code")
  private KoerperexpositionDefiningCode koerperexpositionDefiningCode;

  public void setKoerperexpositionDefiningCode(
      KoerperexpositionDefiningCode koerperexpositionDefiningCode) {
     this.koerperexpositionDefiningCode = koerperexpositionDefiningCode;
  }

  public KoerperexpositionDefiningCode getKoerperexpositionDefiningCode() {
     return this.koerperexpositionDefiningCode ;
  }
}
