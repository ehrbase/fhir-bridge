package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-30T16:13:57.811002+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class AdresseVerwendungDvText implements RMEntity, AdresseVerwendungChoice {
  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Verwendung/Verwendung
   * Description: Der primäre Zweck oder die primäre Verwendung der Adresse.
   */
  @Path("|value")
  private String verwendungValue;

  public void setVerwendungValue(String verwendungValue) {
     this.verwendungValue = verwendungValue;
  }

  public String getVerwendungValue() {
     return this.verwendungValue ;
  }
}
