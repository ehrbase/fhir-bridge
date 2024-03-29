package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.Long;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-20T15:29:31.675846+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_COUNT")
public class LaborergebnisTestmethodeDvCount implements RMEntity, LaborergebnisTestmethodeChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Testmethode/Testmethode
   * Description: Die Beschreibung der Methode, mit dem der Test durchgeführt wurde.
   */
  @Path("|magnitude")
  private Long testmethodeMagnitude;

  public void setTestmethodeMagnitude(Long testmethodeMagnitude) {
     this.testmethodeMagnitude = testmethodeMagnitude;
  }

  public Long getTestmethodeMagnitude() {
     return this.testmethodeMagnitude ;
  }
}
