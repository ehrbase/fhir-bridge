package org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;
import org.hl7.fhir.r4.model.UriType;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-04T14:02:17.169337200+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_URI")
public class ProAnalytZugehoerigeLaborprobeDvUri implements RMEntity, ProAnalytZugehoerigeLaborprobeChoice {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Zugehörige Laborprobe/Zugehörige Laborprobe
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   */
  @Path("|value")
  private UriType zugehoerigeLaborprobeValue;

  public void setZugehoerigeLaborprobeValue(UriType zugehoerigeLaborprobeValue) {
     this.zugehoerigeLaborprobeValue = zugehoerigeLaborprobeValue;
  }

  public UriType getZugehoerigeLaborprobeValue() {
     return this.zugehoerigeLaborprobeValue ;
  }
}
