package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-26T12:59:02.509534285+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_CODED_TEXT")
public class SonstigerVersorgungsfallFallklasseDvCodedText implements RMEntity, SonstigerVersorgungsfallFallklasseChoice {
  /**
   * Path: Sonstiger Versorgungsfall/context/Fallklasse/Fallklasse
   * Description: Nähere Beschreibung des Falls als Fallklasse, z.B. ambulanter Besuch,  stationärer, prä- oder nachstationärer Aufenthalt.
   */
  @Path("|defining_code")
  private FallklasseDefiningCode fallklasseDefiningCode;

  public void setFallklasseDefiningCode(FallklasseDefiningCode fallklasseDefiningCode) {
     this.fallklasseDefiningCode = fallklasseDefiningCode;
  }

  public FallklasseDefiningCode getFallklasseDefiningCode() {
     return this.fallklasseDefiningCode ;
  }
}
