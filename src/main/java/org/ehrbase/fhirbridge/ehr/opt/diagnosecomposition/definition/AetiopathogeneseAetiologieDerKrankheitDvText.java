package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:52:54.873207+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_TEXT")
public class AetiopathogeneseAetiologieDerKrankheitDvText implements RMEntity, AetiopathogeneseAetiologieDerKrankheitChoice {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Ätiologie der Krankheit/Ätiologie der Krankheit
   * Description: Identifizierung der Ursache der Krankheit oder des abnormalen Zustands.
   */
  @Path("|value")
  private String aetiologieDerKrankheitValue;

  public void setAetiologieDerKrankheitValue(String aetiologieDerKrankheitValue) {
     this.aetiologieDerKrankheitValue = aetiologieDerKrankheitValue;
  }

  public String getAetiologieDerKrankheitValue() {
     return this.aetiologieDerKrankheitValue ;
  }
}
