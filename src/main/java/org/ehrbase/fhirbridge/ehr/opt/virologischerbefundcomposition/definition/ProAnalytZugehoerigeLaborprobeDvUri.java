package org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-23T18:23:03.144094476+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_URI")
public class ProAnalytZugehoerigeLaborprobeDvUri implements RMEntity, ProAnalytZugehoerigeLaborprobeChoice {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Zugehörige Laborprobe/Zugehörige Laborprobe
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   * Comment: In manchen Situationen wird ein einzelner Laborergebnis-Archetyp mehrere Probe- und Laboranalyt-Ergebnis-Archetypen enthalten. In diesen Fällen wird dieses "Probe"-Datenelement benötigt, um die Ergebnisse mit den richtigen Proben zu verknüpfen.
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
