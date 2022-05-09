package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:01:54.555132368+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("DV_CODED_TEXT")
public class BlutdruckStelleDerMessungDvCodedText implements RMEntity, BlutdruckStelleDerMessungChoice {
  /**
   * Path: Selbstüberwachung/Blutdruck/Stelle der Messung/Stelle der Messung
   * Description: Einfache Körperstelle an der der Blutdruck gemessen wurde.
   */
  @Path("|defining_code")
  private StelleDerMessungDefiningCode stelleDerMessungDefiningCode;

  public void setStelleDerMessungDefiningCode(
      StelleDerMessungDefiningCode stelleDerMessungDefiningCode) {
     this.stelleDerMessungDefiningCode = stelleDerMessungDefiningCode;
  }

  public StelleDerMessungDefiningCode getStelleDerMessungDefiningCode() {
     return this.stelleDerMessungDefiningCode ;
  }
}
