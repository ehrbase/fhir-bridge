package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.549459+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_CODED_TEXT")
public class ProAnalytValueDvCodedText implements RMEntity, ProAnalytValueChoice2 {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/value/value
   */
  @Path("|defining_code")
  private ValueDefiningCode valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
