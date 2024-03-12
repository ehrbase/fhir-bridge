package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-26T12:59:02.491568392+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_CODED_TEXT")
public class FachlicheOrganisationseinheitTypDvCodedText implements RMEntity, FachlicheOrganisationseinheitTypChoice {
  /**
   * Path: Sonstiger Versorgungsfall/context/Fachliche Organisationseinheit/Typ/Typ
   * Description: Art der Organisationseinheit.
   * Comment: Zum Beispiel: Fachabteilung im Krankenhaus, Versicherungsunternehmen, Sponsor
   */
  @Path("|defining_code")
  private TypDefiningCode typDefiningCode;

  public void setTypDefiningCode(TypDefiningCode typDefiningCode) {
     this.typDefiningCode = typDefiningCode;
  }

  public TypDefiningCode getTypDefiningCode() {
     return this.typDefiningCode ;
  }
}
