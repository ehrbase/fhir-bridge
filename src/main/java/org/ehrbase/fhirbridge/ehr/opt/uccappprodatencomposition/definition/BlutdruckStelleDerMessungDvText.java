package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:01:54.560907308+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("DV_TEXT")
public class BlutdruckStelleDerMessungDvText implements RMEntity, BlutdruckStelleDerMessungChoice {
  /**
   * Path: Selbstüberwachung/Blutdruck/Stelle der Messung/Stelle der Messung
   * Description: Einfache Körperstelle an der der Blutdruck gemessen wurde.
   */
  @Path("|value")
  private String stelleDerMessungValue;

  public void setStelleDerMessungValue(String stelleDerMessungValue) {
     this.stelleDerMessungValue = stelleDerMessungValue;
  }

  public String getStelleDerMessungValue() {
     return this.stelleDerMessungValue ;
  }
}
