package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.161838782+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_URI")
public class ElektronischeKommunikationDatenDvUri implements RMEntity, ElektronischeKommunikationDatenChoice {
  /**
   * Path: Person/Personendaten/Kontaktperson/Elektronische Kommunikation/Daten/Daten
   * Description: Die eindeutige Kombination alphanumerischer Zeichen, die für die Darstellung von "Art" relevant ist.
   * Comment: Zum Beispiel: Vorwahl + Telefon/Pagernummer; Ländervorwahl + Mobiltelefonnummer oder E-Mail-Adresse.
   */
  @Path("|value")
  private URI datenValue;

  public void setDatenValue(URI datenValue) {
     this.datenValue = datenValue;
  }

  public URI getDatenValue() {
     return this.datenValue ;
  }
}
