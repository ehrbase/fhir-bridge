package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-24T22:02:30.843694+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class MediendateiIdentifikatorDvText implements RMEntity, MediendateiIdentifikatorChoice {
  /**
   * Path: Bericht/context/Mediendatei/Identifikator/Identifikator
   * Description: Eindeutige ID für die Mediendatei.
   */
  @Path("|value")
  private String identifikatorValue;

  public void setIdentifikatorValue(String identifikatorValue) {
     this.identifikatorValue = identifikatorValue;
  }

  public String getIdentifikatorValue() {
     return this.identifikatorValue ;
  }
}
