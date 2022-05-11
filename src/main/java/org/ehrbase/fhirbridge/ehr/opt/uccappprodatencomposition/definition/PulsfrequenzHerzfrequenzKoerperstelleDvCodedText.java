package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-10T17:43:37.188832573+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("DV_CODED_TEXT")
public class PulsfrequenzHerzfrequenzKoerperstelleDvCodedText implements RMEntity, PulsfrequenzHerzfrequenzKoerperstelleChoice {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Körperstelle/Körperstelle
   * Description: Die Körperstelle an der die Puls- oder die Herzfrequenz gemessen wird.
   */
  @Path("|defining_code")
  private KoerperstelleDefiningCode koerperstelleDefiningCode;

  public void setKoerperstelleDefiningCode(KoerperstelleDefiningCode koerperstelleDefiningCode) {
     this.koerperstelleDefiningCode = koerperstelleDefiningCode;
  }

  public KoerperstelleDefiningCode getKoerperstelleDefiningCode() {
     return this.koerperstelleDefiningCode ;
  }
}
