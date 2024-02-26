package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-26T12:59:02.493725319+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_TEXT")
public class FachlicheOrganisationseinheitTypDvText implements RMEntity, FachlicheOrganisationseinheitTypChoice {
  /**
   * Path: Sonstiger Versorgungsfall/context/Fachliche Organisationseinheit/Typ/Typ
   * Description: Art der Organisationseinheit.
   * Comment: Zum Beispiel: Fachabteilung im Krankenhaus, Versicherungsunternehmen, Sponsor
   */
  @Path("|value")
  private String typValue;

  public void setTypValue(String typValue) {
     this.typValue = typValue;
  }

  public String getTypValue() {
     return this.typValue ;
  }
}
