package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-20T15:29:31.643090+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_DATE_TIME")
public class ProLaboranalytTestmethodeDvDateTime implements RMEntity, ProLaboranalytTestmethodeChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Testmethode/Testmethode
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
