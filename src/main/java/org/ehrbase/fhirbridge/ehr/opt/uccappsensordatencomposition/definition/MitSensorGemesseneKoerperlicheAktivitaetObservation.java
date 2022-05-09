package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.wearable_sensor_activity.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:12:33.425007197+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class MitSensorGemesseneKoerperlicheAktivitaetObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Gerät
   * Description: Details über den verwendeten Sensor, der zur Aufzeichnung der Aktivität verwendet wurde.
   */
  @Path("/protocol[at0011]/items[at0015]")
  private List<Cluster> geraet;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Informationen zu Hard- und Software
   * Description: Weitere Informationen zur Rahmenbedingung der Aktivitätsmessung.
   */
  @Path("/protocol[at0011]/items[at0013 and name/value='Informationen zu Hard- und Software']/value|value")
  private String informationenZuHardUndSoftwareValue;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Baum/Informationen zu Hard- und Software/null_flavour
   */
  @Path("/protocol[at0011]/items[at0013 and name/value='Informationen zu Hard- und Software']/null_flavour|defining_code")
  private NullFlavour informationenZuHardUndSoftwareNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis
   * Description: Ein Standardwert, ein spezifizierter Zeitpunkt oder ein Intervallereignis, welches explizit in einem Template oder während der Laufzeit definiert werden kann.
   */
  @Path("/data[at0001]/events[at0002]")
  @Choice
  private List<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice> jedesEreignis;

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setGeraet(List<Cluster> geraet) {
     this.geraet = geraet;
  }

  public List<Cluster> getGeraet() {
     return this.geraet ;
  }

  public void setInformationenZuHardUndSoftwareValue(String informationenZuHardUndSoftwareValue) {
     this.informationenZuHardUndSoftwareValue = informationenZuHardUndSoftwareValue;
  }

  public String getInformationenZuHardUndSoftwareValue() {
     return this.informationenZuHardUndSoftwareValue ;
  }

  public void setInformationenZuHardUndSoftwareNullFlavourDefiningCode(
      NullFlavour informationenZuHardUndSoftwareNullFlavourDefiningCode) {
     this.informationenZuHardUndSoftwareNullFlavourDefiningCode = informationenZuHardUndSoftwareNullFlavourDefiningCode;
  }

  public NullFlavour getInformationenZuHardUndSoftwareNullFlavourDefiningCode() {
     return this.informationenZuHardUndSoftwareNullFlavourDefiningCode ;
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

  public void setJedesEreignis(
      List<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice> jedesEreignis) {
     this.jedesEreignis = jedesEreignis;
  }

  public List<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice> getJedesEreignis() {
     return this.jedesEreignis ;
  }
}
