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
    date = "2022-05-09T16:40:29.045293820+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("DV_TEXT")
public class BloodPressureLocationOfMeasurementDvText implements RMEntity, BloodPressureLocationOfMeasurementChoice {
  /**
   * Path: Self monitoring/Blood pressure/Location of measurement/Location of measurement
   * Description: Simple body site where blood pressure was measured.
   */
  @Path("|value")
  private String locationOfMeasurementValue;

  public void setLocationOfMeasurementValue(String locationOfMeasurementValue) {
     this.locationOfMeasurementValue = locationOfMeasurementValue;
  }

  public String getLocationOfMeasurementValue() {
     return this.locationOfMeasurementValue ;
  }
}
