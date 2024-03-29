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
    date = "2021-10-04T14:02:17.076341+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class BefundAuftragsIdEmpfaengerDvText implements RMEntity, BefundAuftragsIdEmpfaengerChoice {
  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Auftrags-ID (Empfänger)/Auftrags-ID (Empfänger)
   * Description: Lokale Auftrags-ID, die vom auftragsempfangendem System, gewöhnlich dem Laborinformationssystem (LIS) zugewiesen wird.
   */
  @Path("|value")
  private String auftragsIdEmpfaengerValue;

  public void setAuftragsIdEmpfaengerValue(String auftragsIdEmpfaengerValue) {
     this.auftragsIdEmpfaengerValue = auftragsIdEmpfaengerValue;
  }

  public String getAuftragsIdEmpfaengerValue() {
     return this.auftragsIdEmpfaengerValue ;
  }
}
