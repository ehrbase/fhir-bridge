package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.232406+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_IDENTIFIER")
public class ProErregerZugehoerigeLaborprobeDvIdentifier implements RMEntity, ProErregerZugehoerigeLaborprobeChoice {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Zugehörige Laborprobe/Zugehörige Laborprobe
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   * Comment: In manchen Situationen wird ein einzelner Laborergebnis-Archetyp mehrere Probe- und Laboranalyt-Ergebnis-Archetypen enthalten. In diesen Fällen wird dieses "Probe"-Datenelement benötigt, um die Ergebnisse mit den richtigen Proben zu verknüpfen.
   */
  @Path("")
  private DvIdentifier zugehoerigeLaborprobe;

  public void setZugehoerigeLaborprobe(DvIdentifier zugehoerigeLaborprobe) {
     this.zugehoerigeLaborprobe = zugehoerigeLaborprobe;
  }

  public DvIdentifier getZugehoerigeLaborprobe() {
     return this.zugehoerigeLaborprobe ;
  }
}
