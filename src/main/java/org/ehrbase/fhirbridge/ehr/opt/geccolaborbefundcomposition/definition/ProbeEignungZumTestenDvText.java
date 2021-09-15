package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-08T14:37:10.969118+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class ProbeEignungZumTestenDvText implements RMEntity, ProbeEignungZumTestenChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Eignung zum Testen/Eignung zum Testen
   * Description: Angabe, ob die Probe für die Untersuchung geeignet war.
   */
  @Path("|value")
  private String eignungZumTestenValue;

  public void setEignungZumTestenValue(String eignungZumTestenValue) {
     this.eignungZumTestenValue = eignungZumTestenValue;
  }

  public String getEignungZumTestenValue() {
     return this.eignungZumTestenValue ;
  }
}
