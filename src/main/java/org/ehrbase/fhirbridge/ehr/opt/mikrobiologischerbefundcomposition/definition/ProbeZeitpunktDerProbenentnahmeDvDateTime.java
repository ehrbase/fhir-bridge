package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.196192+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_DATE_TIME")
public class ProbeZeitpunktDerProbenentnahmeDvDateTime implements RMEntity, ProbeZeitpunktDerProbenentnahmeChoice {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/Zeitpunkt der Probenentnahme
   * Description: Das Datum und die Uhrzeit, zu der die Probennahme angeordnet wurde oder stattfand.
   * Comment: Dieser Zeitpunkt wird hauptsächlich in den Zeitangaben, von INSTRUCTION, ACTION oder OBSERVATION erfasst. Da es sich jedoch um eine wichtige Information handelt, kann es nützlich sein, diese auch direkt mit der Probe selbst zu verknüpfen.
   */
  @Path("|value")
  private TemporalAccessor zeitpunktDerProbenentnahmeValue;

  public void setZeitpunktDerProbenentnahmeValue(TemporalAccessor zeitpunktDerProbenentnahmeValue) {
     this.zeitpunktDerProbenentnahmeValue = zeitpunktDerProbenentnahmeValue;
  }

  public TemporalAccessor getZeitpunktDerProbenentnahmeValue() {
     return this.zeitpunktDerProbenentnahmeValue ;
  }
}
