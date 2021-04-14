package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:52:54.871213+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_CODED_TEXT")
public class AetiopathogeneseAetiologieDerKrankheitDvCodedText implements RMEntity, AetiopathogeneseAetiologieDerKrankheitChoice {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Ätiologie der Krankheit/Ätiologie der Krankheit
   * Description: Identifizierung der Ursache der Krankheit oder des abnormalen Zustands.
   */
  @Path("|defining_code")
  private AetiologieDerKrankheitDefiningCode aetiologieDerKrankheitDefiningCode;

  public void setAetiologieDerKrankheitDefiningCode(
      AetiologieDerKrankheitDefiningCode aetiologieDerKrankheitDefiningCode) {
     this.aetiologieDerKrankheitDefiningCode = aetiologieDerKrankheitDefiningCode;
  }

  public AetiologieDerKrankheitDefiningCode getAetiologieDerKrankheitDefiningCode() {
     return this.aetiologieDerKrankheitDefiningCode ;
  }
}
