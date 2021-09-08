package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-08T14:37:11.051414+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_URI")
public class LaborergebnisTestmethodeDvUri implements RMEntity, LaborergebnisTestmethodeChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Testmethode/Testmethode
   * Description: Die Beschreibung der Methode, mit dem der Test durchgeführt wurde.
   */
  @Path("|value")
  private URI testmethodeValue;

  public void setTestmethodeValue(URI testmethodeValue) {
     this.testmethodeValue = testmethodeValue;
  }

  public URI getTestmethodeValue() {
     return this.testmethodeValue ;
  }
}
