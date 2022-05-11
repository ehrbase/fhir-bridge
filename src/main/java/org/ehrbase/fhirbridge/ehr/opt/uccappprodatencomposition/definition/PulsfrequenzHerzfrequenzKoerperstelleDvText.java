package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-10T17:43:37.191148670+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("DV_TEXT")
public class PulsfrequenzHerzfrequenzKoerperstelleDvText implements RMEntity, PulsfrequenzHerzfrequenzKoerperstelleChoice {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Körperstelle/Körperstelle
   * Description: Die Körperstelle an der die Puls- oder die Herzfrequenz gemessen wird.
   */
  @Path("|value")
  private String koerperstelleValue;

  public void setKoerperstelleValue(String koerperstelleValue) {
     this.koerperstelleValue = koerperstelleValue;
  }

  public String getKoerperstelleValue() {
     return this.koerperstelleValue ;
  }
}
