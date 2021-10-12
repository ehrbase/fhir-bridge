package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T14:49:10.178876+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_CODED_TEXT")
public class AdresseVerwendungDvCodedText implements RMEntity, AdresseVerwendungChoice {
  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Verwendung/Verwendung
   * Description: Der primäre Zweck oder die primäre Verwendung der Adresse.
   */
  @Path("|defining_code")
  private VerwendungDefiningCode verwendungDefiningCode;

  public void setVerwendungDefiningCode(VerwendungDefiningCode verwendungDefiningCode) {
     this.verwendungDefiningCode = verwendungDefiningCode;
  }

  public VerwendungDefiningCode getVerwendungDefiningCode() {
     return this.verwendungDefiningCode ;
  }
}
