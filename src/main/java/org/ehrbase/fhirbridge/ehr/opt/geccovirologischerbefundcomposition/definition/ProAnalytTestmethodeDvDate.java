package org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition;

import java.time.temporal.Temporal;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-28T15:04:42.673441+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_DATE")
public class ProAnalytTestmethodeDvDate implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Testmethode/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   */
  @Path("|value")
  private Temporal testmethodeValue;

  public void setTestmethodeValue(Temporal testmethodeValue) {
     this.testmethodeValue = testmethodeValue;
  }

  public Temporal getTestmethodeValue() {
     return this.testmethodeValue ;
  }
}
