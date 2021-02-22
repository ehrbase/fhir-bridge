package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.540585+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_MULTIMEDIA")
public class ProAnalytValueDvMultimedia implements RMEntity, ProAnalytValueChoice {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/value/value
   */
  @Path("")
  private DvMultimedia value;

  public void setValue(DvMultimedia value) {
     this.value = value;
  }

  public DvMultimedia getValue() {
     return this.value ;
  }
}
