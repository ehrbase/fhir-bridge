package org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition;

import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-04T14:02:17.164338+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_DATE_TIME")
public class ProAnalytTestmethodeDvDateTime implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Testmethode/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   */
  @Path("|value")
  private TemporalAccessor testmethodeValue;

  public void setTestmethodeValue(TemporalAccessor testmethodeValue) {
     this.testmethodeValue = testmethodeValue;
  }

  public TemporalAccessor getTestmethodeValue() {
     return this.testmethodeValue ;
  }
}
