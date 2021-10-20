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
    date = "2021-10-20T12:36:29.068491+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_URI")
public class ProLaboranalytProbeIdDvUri implements RMEntity, ProLaboranalytProbeIdChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Probe ID/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   */
  @Path("|value")
  private URI probeIdValue;

  public void setProbeIdValue(URI probeIdValue) {
     this.probeIdValue = probeIdValue;
  }

  public URI getProbeIdValue() {
     return this.probeIdValue ;
  }
}
