package org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-23T18:23:03.109307704+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_TEXT")
public class BefundAuftragsIdDesAnforderndenEinsendendenSystemsDvText implements RMEntity, BefundAuftragsIdDesAnforderndenEinsendendenSystemsChoice {
  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Auftrags-ID des anfordernden/einsendenden Systems/Auftrags-ID des anfordernden/einsendenden Systems
   * Description: Lokale Auftrags-ID des anfordernden/einsendenden Systems.
   * Comment: Äquivalent zur "HL7 Placer Order Identifier".
   */
  @Path("|value")
  private String auftragsIdDesAnforderndenEinsendendenSystemsValue;

  public void setAuftragsIdDesAnforderndenEinsendendenSystemsValue(
      String auftragsIdDesAnforderndenEinsendendenSystemsValue) {
     this.auftragsIdDesAnforderndenEinsendendenSystemsValue = auftragsIdDesAnforderndenEinsendendenSystemsValue;
  }

  public String getAuftragsIdDesAnforderndenEinsendendenSystemsValue() {
     return this.auftragsIdDesAnforderndenEinsendendenSystemsValue ;
  }
}
