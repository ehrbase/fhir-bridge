package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import java.lang.Boolean;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-24T14:43:52.686263623+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
@OptionFor("DV_INTERVAL<DV_DATE_TIME>")
public class ProbenmaterialZeitpunktDerProbenentnahmeDvIntervalDvDateTime implements RMEntity, ProbenmaterialZeitpunktDerProbenentnahmeChoice {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zeitpunkt der Probenentnahme/lower_included
   */
  @Path("/lower_included")
  private Boolean lowerIncluded;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zeitpunkt der Probenentnahme/upper_included
   */
  @Path("/upper_included")
  private Boolean upperIncluded;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zeitpunkt der Probenentnahme/upper
   */
  @Path("/upper")
  private DvOrdered upper;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zeitpunkt der Probenentnahme/lower
   */
  @Path("/lower")
  private DvOrdered lower;

  public void setLowerIncluded(Boolean lowerIncluded) {
     this.lowerIncluded = lowerIncluded;
  }

  public Boolean isLowerIncluded() {
     return this.lowerIncluded ;
  }

  public void setUpperIncluded(Boolean upperIncluded) {
     this.upperIncluded = upperIncluded;
  }

  public Boolean isUpperIncluded() {
     return this.upperIncluded ;
  }

  public void setUpper(DvOrdered upper) {
     this.upper = upper;
  }

  public DvOrdered getUpper() {
     return this.upper ;
  }

  public void setLower(DvOrdered lower) {
     this.lower = lower;
  }

  public DvOrdered getLower() {
     return this.lower ;
  }
}
