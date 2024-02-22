package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import java.time.temporal.Temporal;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.223255+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_DATE")
public class ProAntibiotikumTestmethodeDvDate implements RMEntity, ProAntibiotikumTestmethodeChoice {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Testmethode/Testmethode
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
