package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.550759+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_TEXT")
public class ProAnalytValueDvText implements RMEntity, ProAnalytValueChoice2 {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/value/value
   */
  @Path("|value")
  private String valueValue;

  public void setValueValue(String valueValue) {
     this.valueValue = valueValue;
  }

  public String getValueValue() {
     return this.valueValue ;
  }
}
