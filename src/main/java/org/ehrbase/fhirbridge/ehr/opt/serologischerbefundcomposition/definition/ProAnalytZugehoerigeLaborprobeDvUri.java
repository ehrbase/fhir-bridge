package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.557006+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_URI")
public class ProAnalytZugehoerigeLaborprobeDvUri implements RMEntity, ProAnalytZugehoerigeLaborprobeChoice {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/value/value
   */
  @Path("|value")
  private URI zugehoerigeLaborprobeValue;

  public void setZugehoerigeLaborprobeValue(URI zugehoerigeLaborprobeValue) {
     this.zugehoerigeLaborprobeValue = zugehoerigeLaborprobeValue;
  }

  public URI getZugehoerigeLaborprobeValue() {
     return this.zugehoerigeLaborprobeValue ;
  }
}
