package org.ehrbase.fhirbridge.ehr.template.geccolaborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class ProbeEinsendendenSystemsDvtext implements ProbeEinsendendenSystemsChoice {
  @Path("|value")
  private String einsendendenSystemsValue;

  public void setEinsendendenSystemsValue(String einsendendenSystemsValue) {
     this.einsendendenSystemsValue = einsendendenSystemsValue;
  }

  public String getEinsendendenSystemsValue() {
     return this.einsendendenSystemsValue ;
  }
}
