package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
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
@Archetype("openEHR-EHR-OBSERVATION.pulse.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-10T17:43:37.181520751+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class PulsfrequenzHerzfrequenzObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double frequenzMagnitude;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String frequenzUnits;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/History/Jedes Ereignis/Structure/Frequenz/null_flavour
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour frequenzNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Belastungsgrad
   * Description: Aufzeichnung von Informationen zum Belastungsgrad/Zustand des Patienten.
   */
  @Path("/data[at0002]/events[at0003]/state[at0012]/items[openEHR-EHR-CLUSTER.level_of_exertion.v0]")
  private List<BelastungsgradCluster2> belastungsgrad;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Methode
   * Description: Die Methode, mit der die Puls- oder Herzfrequenz gemessen wurde.
   * Comment: Zum Beispiel auskultatorisch oder über elektronisches Monitoring, z.B. mit einem Blutdruckmessgerät.
   */
  @Path("/protocol[at0010]/items[at1019]/value|defining_code")
  private MethodeDefiningCode methodeDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/List/Methode/null_flavour
   */
  @Path("/protocol[at0010]/items[at1019]/null_flavour|defining_code")
  private NullFlavour methodeNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/List/Körperstelle/null_flavour
   */
  @Path("/protocol[at0010]/items[at1037]/null_flavour|defining_code")
  private NullFlavour koerperstelleNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Gerät
   * Description: Informationen zu dem Gerät, welches zur Messung der Puls- oder der Herzfrequenz verwendet wurde.
   */
  @Path("/protocol[at0010]/items[at1013]")
  private Cluster geraet;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Körperstelle
   * Description: Die Körperstelle an der die Puls- oder die Herzfrequenz gemessen wird.
   */
  @Path("/protocol[at0010]/items[at1037]/value")
  @Choice
  private PulsfrequenzHerzfrequenzKoerperstelleChoice koerperstelle;

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

  public void setBelastungsgrad(List<BelastungsgradCluster2> belastungsgrad) {
     this.belastungsgrad = belastungsgrad;
  }

  public List<BelastungsgradCluster2> getBelastungsgrad() {
     return this.belastungsgrad ;
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

  public void setMethodeDefiningCode(MethodeDefiningCode methodeDefiningCode) {
     this.methodeDefiningCode = methodeDefiningCode;
  }

  public MethodeDefiningCode getMethodeDefiningCode() {
     return this.methodeDefiningCode ;
  }

  public void setMethodeNullFlavourDefiningCode(NullFlavour methodeNullFlavourDefiningCode) {
     this.methodeNullFlavourDefiningCode = methodeNullFlavourDefiningCode;
  }

  public NullFlavour getMethodeNullFlavourDefiningCode() {
     return this.methodeNullFlavourDefiningCode ;
  }

  public void setKoerperstelleNullFlavourDefiningCode(
      NullFlavour koerperstelleNullFlavourDefiningCode) {
     this.koerperstelleNullFlavourDefiningCode = koerperstelleNullFlavourDefiningCode;
  }

  public NullFlavour getKoerperstelleNullFlavourDefiningCode() {
     return this.koerperstelleNullFlavourDefiningCode ;
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

  public void setKoerperstelle(PulsfrequenzHerzfrequenzKoerperstelleChoice koerperstelle) {
     this.koerperstelle = koerperstelle;
  }

  public PulsfrequenzHerzfrequenzKoerperstelleChoice getKoerperstelle() {
     return this.koerperstelle ;
  }
}
