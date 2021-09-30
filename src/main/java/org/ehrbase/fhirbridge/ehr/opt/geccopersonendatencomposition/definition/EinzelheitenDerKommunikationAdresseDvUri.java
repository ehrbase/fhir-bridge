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
    date = "2021-09-30T16:13:57.844517+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
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
