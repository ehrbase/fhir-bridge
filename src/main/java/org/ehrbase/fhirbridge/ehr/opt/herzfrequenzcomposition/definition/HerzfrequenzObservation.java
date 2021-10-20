package org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pulse.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-19T12:27:57.248926+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class HerzfrequenzObservation implements EntryEntity {
  /**
   * Path: Herzfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double frequenzMagnitude;

  /**
   * Path: Herzfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String frequenzUnits;

  /**
   * Path: Herzfrequenz/Herzfrequenz/History/Jedes Ereignis/Structure/Frequenz/null_flavour
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour frequenzNullFlavourDefiningCode;

  /**
   * Path: Herzfrequenz/Herzfrequenz/Jedes Ereignis/Anstrengung
   * Description: Details über die körperliche Anstrengung, die der Patient während der Untersuchung ausgesetzt war.
   */
  @Path("/data[at0002]/events[at0003]/state[at0012]/items[at1017]")
  private List<Cluster> anstrengung;

  /**
   * Path: Herzfrequenz/Herzfrequenz/Jedes Ereignis/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Herzfrequenz/Herzfrequenz/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Herzfrequenz/Herzfrequenz/Gerät
   * Description: Informationen zu dem Gerät, welches zur Messung der Puls- oder der Herzfrequenz verwendet wurde.
   */
  @Path("/protocol[at0010]/items[at1013]")
  private Cluster geraet;

  /**
   * Path: Herzfrequenz/Herzfrequenz/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> erweiterung;

  /**
   * Path: Herzfrequenz/Herzfrequenz/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Herzfrequenz/Herzfrequenz/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Herzfrequenz/Herzfrequenz/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setFrequenzMagnitude(Double frequenzMagnitude) {
     this.frequenzMagnitude = frequenzMagnitude;
  }

  public Double getFrequenzMagnitude() {
     return this.frequenzMagnitude ;
  }

  public void setFrequenzUnits(String frequenzUnits) {
     this.frequenzUnits = frequenzUnits;
  }

  public String getFrequenzUnits() {
     return this.frequenzUnits ;
  }

  public void setFrequenzNullFlavourDefiningCode(NullFlavour frequenzNullFlavourDefiningCode) {
     this.frequenzNullFlavourDefiningCode = frequenzNullFlavourDefiningCode;
  }

  public NullFlavour getFrequenzNullFlavourDefiningCode() {
     return this.frequenzNullFlavourDefiningCode ;
  }

  public void setAnstrengung(List<Cluster> anstrengung) {
     this.anstrengung = anstrengung;
  }

  public List<Cluster> getAnstrengung() {
     return this.anstrengung ;
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

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
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
