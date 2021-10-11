package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.medication_statement.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T15:28:13.017633+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ImmunglobulineObservation implements EntryEntity {
  /**
   * Path: Medikation/Immunglobuline/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Medikation/Immunglobuline/Status
   * Description: Ein Code, der die Beurteilung des Patienten oder einer anderen Beurteilungsquelle über den Status des angewendeten Medikaments darstellt, mit dem sich diese Angabe befasst. Im Allgemeinen wird dies aktiv oder abgeschlossen sein.
   */
  @Path("/protocol[at0004]/items[openEHR-EHR-CLUSTER.medication_status_fhir.v0 and name/value='Status']")
  private StatusCluster status;

  /**
   * Path: Medikation/Immunglobuline/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Medikation/Immunglobuline/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Medikation/Immunglobuline/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis
   * Description: Standardwert, ein undefinierter/s Zeitpunkt oder Intervallereignis, das explizit im Template oder zur Laufzeit der Anwendung definiert werden kann.
   */
  @Path("/data[at0001]/events[at0002]")
  @Choice
  private List<ImmunglobulineBeliebigesEreignisChoice> beliebigesEreignis;

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setStatus(StatusCluster status) {
     this.status = status;
  }

  public StatusCluster getStatus() {
     return this.status ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setBeliebigesEreignis(
      List<ImmunglobulineBeliebigesEreignisChoice> beliebigesEreignis) {
     this.beliebigesEreignis = beliebigesEreignis;
  }

  public List<ImmunglobulineBeliebigesEreignisChoice> getBeliebigesEreignis() {
     return this.beliebigesEreignis ;
  }
}
