package org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-14T14:30:10.464147400+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_IDENTIFIER")
public class BefundAuftragsIdDesAnforderndenEinsendendenSystemsDvIdentifier implements RMEntity, BefundAuftragsIdDesAnforderndenEinsendendenSystemsChoice {
  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Auftrags-ID des anfordernden/einsendenden Systems/Auftrags-ID des anfordernden/einsendenden Systems
   * Description: Lokale Auftrags-ID des anfordernden/einsendenden Systems.
   */
  @Path("")
  private DvIdentifier auftragsIdDesAnforderndenEinsendendenSystems;

  public void setAuftragsIdDesAnforderndenEinsendendenSystems(
      DvIdentifier auftragsIdDesAnforderndenEinsendendenSystems) {
     this.auftragsIdDesAnforderndenEinsendendenSystems = auftragsIdDesAnforderndenEinsendendenSystems;
  }

  public DvIdentifier getAuftragsIdDesAnforderndenEinsendendenSystems() {
     return this.auftragsIdDesAnforderndenEinsendendenSystems ;
  }
}
