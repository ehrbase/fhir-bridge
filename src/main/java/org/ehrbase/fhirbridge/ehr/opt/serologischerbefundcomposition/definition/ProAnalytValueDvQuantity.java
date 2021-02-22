package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

import java.lang.Double;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.541152+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_QUANTITY")
public class ProAnalytValueDvQuantity implements RMEntity, ProAnalytValueChoice {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/value/value
   */
  @Path("|magnitude")
  private Double valueMagnitude;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/value/value
   */
  @Path("|units")
  private String valueUnits;

  public void setValueMagnitude(Double valueMagnitude) {
     this.valueMagnitude = valueMagnitude;
  }

  public Double getValueMagnitude() {
     return this.valueMagnitude ;
  }

  public void setValueUnits(String valueUnits) {
     this.valueUnits = valueUnits;
  }

  public String getValueUnits() {
     return this.valueUnits ;
  }
}
