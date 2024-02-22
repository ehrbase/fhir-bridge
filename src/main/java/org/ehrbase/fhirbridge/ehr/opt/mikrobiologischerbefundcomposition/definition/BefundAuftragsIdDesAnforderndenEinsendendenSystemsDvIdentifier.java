package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.244813+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_IDENTIFIER")
public class BefundAuftragsIdDesAnforderndenEinsendendenSystemsDvIdentifier implements RMEntity, BefundAuftragsIdDesAnforderndenEinsendendenSystemsChoice {
  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/Auftrags-ID des anfordernden/einsendenden Systems/Auftrags-ID des anfordernden/einsendenden Systems
   * Description: Lokale Auftrags-ID des anfordernden/einsendenden Systems.
   * Comment: Äquivalent zur "HL7 Placer Order Identifier".
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
