package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-26T12:59:02.510422774+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_TEXT")
public class SonstigerVersorgungsfallFallklasseDvText implements RMEntity, SonstigerVersorgungsfallFallklasseChoice {
  /**
   * Path: Sonstiger Versorgungsfall/context/Fallklasse/Fallklasse
   * Description: Nähere Beschreibung des Falls als Fallklasse, z.B. ambulanter Besuch,  stationärer, prä- oder nachstationärer Aufenthalt.
   */
  @Path("|value")
  private String fallklasseValue;

  public void setFallklasseValue(String fallklasseValue) {
     this.fallklasseValue = fallklasseValue;
  }

  public String getFallklasseValue() {
     return this.fallklasseValue ;
  }
}
