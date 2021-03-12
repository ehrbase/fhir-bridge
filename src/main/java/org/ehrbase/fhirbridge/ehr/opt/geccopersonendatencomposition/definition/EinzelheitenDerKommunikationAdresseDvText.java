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
    date = "2021-02-04T14:52:22.402436500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("DV_TEXT")
public class EinzelheitenDerKommunikationAdresseDvText implements RMEntity, EinzelheitenDerKommunikationAdresseChoice {
  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Internet-Kommunikation/value/value
   */
  @Path("|value")
  private String adresseValue;

  public void setAdresseValue(String adresseValue) {
     this.adresseValue = adresseValue;
  }

  public String getAdresseValue() {
     return this.adresseValue ;
  }
}
