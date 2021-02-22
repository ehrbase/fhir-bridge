package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.454751+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_IDENTIFIER")
public class BefundAuftragsIdEmpfaengerDvIdentifier implements RMEntity, BefundAuftragsIdEmpfaengerChoice {
  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/value/value
   */
  @Path("")
  private DvIdentifier auftragsIdEmpfaenger;

  public void setAuftragsIdEmpfaenger(DvIdentifier auftragsIdEmpfaenger) {
     this.auftragsIdEmpfaenger = auftragsIdEmpfaenger;
  }

  public DvIdentifier getAuftragsIdEmpfaenger() {
     return this.auftragsIdEmpfaenger ;
  }
}
