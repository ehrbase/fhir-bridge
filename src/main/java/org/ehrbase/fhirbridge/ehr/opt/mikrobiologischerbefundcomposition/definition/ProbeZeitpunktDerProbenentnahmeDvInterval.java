package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

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
    date = "2024-02-22T14:23:00.196867+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_INTERVAL")
public class ProbeZeitpunktDerProbenentnahmeDvInterval implements RMEntity, ProbeZeitpunktDerProbenentnahmeChoice {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/lower_included
   */
  @Path("/lower_included")
  private Boolean lowerIncluded;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/upper_included
   */
  @Path("/upper_included")
  private Boolean upperIncluded;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/upper
   */
  @Path("/upper")
  private DvOrdered upper;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/lower
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
