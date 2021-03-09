package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:53:36.977769+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_URI")
public class EinzelheitenDerKommunikationAdresseDvUri implements RMEntity, EinzelheitenDerKommunikationAdresseChoice {
  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Internet-Kommunikation/Adresse/Adresse
   * Description: Die Adresse oder Kennung, die zur Kommunikation auf dem angegebenen Kommunikationsweg verwendet wird.
   */
  @Path("|value")
  private URI adresseValue;

  public void setAdresseValue(URI adresseValue) {
     this.adresseValue = adresseValue;
  }

  public URI getAdresseValue() {
     return this.adresseValue ;
  }
}
