package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.556449+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_IDENTIFIER")
public class ProAnalytZugehoerigeLaborprobeDvIdentifier implements RMEntity, ProAnalytZugehoerigeLaborprobeChoice {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/value/value
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
