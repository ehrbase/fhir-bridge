package org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.ventilator_vital_signs.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:51:09.764650+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class BeobachtungenAmBeatmungsgeraetObservation implements EntryEntity {
  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Jedes Ereignis/Eingeatmeter Sauerstoff
   * Description: Die Sauerstoffmenge, die dem Patienten als Fraktion, als Prozentsatz oder indirekt als Flussrate verabreicht wird oder verabreicht werden soll.
   *
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.inspired_oxygen.v1]")
  private EingeatmeterSauerstoffCluster eingeatmeterSauerstoff;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Jedes Ereignis/Einstellungen des Beatmungsgerätes
   * Description: Einstellungen des Beatmungsgerätes, die vom Beatmungsgerät zurückgegeben werden.
   */
  @Path("/data[at0001]/events[at0002]/state[at0010]/items[at0011]")
  private Cluster einstellungenDesBeatmungsgeraetes;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Jedes Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Gerät
   * Description: Das Gerät gibt Vitalparameter und Einstellungsdaten zurück.
   */
  @Path("/protocol[at0012]/items[at0014]")
  private Cluster geraet;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setEingeatmeterSauerstoff(EingeatmeterSauerstoffCluster eingeatmeterSauerstoff) {
     this.eingeatmeterSauerstoff = eingeatmeterSauerstoff;
  }

  public EingeatmeterSauerstoffCluster getEingeatmeterSauerstoff() {
     return this.eingeatmeterSauerstoff ;
  }

  public void setEinstellungenDesBeatmungsgeraetes(Cluster einstellungenDesBeatmungsgeraetes) {
     this.einstellungenDesBeatmungsgeraetes = einstellungenDesBeatmungsgeraetes;
  }

  public Cluster getEinstellungenDesBeatmungsgeraetes() {
     return this.einstellungenDesBeatmungsgeraetes ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setGeraet(Cluster geraet) {
     this.geraet = geraet;
  }

  public Cluster getGeraet() {
     return this.geraet ;
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
}
