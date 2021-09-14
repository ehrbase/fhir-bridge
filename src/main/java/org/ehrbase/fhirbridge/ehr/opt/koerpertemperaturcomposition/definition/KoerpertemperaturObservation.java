package org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-06T14:20:39.071415+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class KoerpertemperaturObservation implements EntryEntity {
  /**
   * Path: Registereintrag/Koerpertemperatur/Beliebiges Ereignis/Temperatur
   * Description: Die gemessene Körpertemperatur.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double temperaturMagnitude;

  /**
   * Path: Registereintrag/Koerpertemperatur/Beliebiges Ereignis/Temperatur
   * Description: Die gemessene Körpertemperatur.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String temperaturUnits;

  /**
   * Path: Registereintrag/Koerpertemperatur/History/Beliebiges Ereignis/Single/Temperatur/null_flavour
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour temperaturNullFlavourDefiningCode;

  /**
   * Path: Registereintrag/Koerpertemperatur/Beliebiges Ereignis/Umgebungsbedingungen
   * Description: Details über die Umgebungsbedingungen zum Zeitpunkt der Temperaturmessung
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0056]")
  private List<Cluster> umgebungsbedingungen;

  /**
   * Path: Registereintrag/Koerpertemperatur/Beliebiges Ereignis/Betätigung
   * Description: Details über die Betätigung der Person zum Zeitpunkt der Messung der Temperatur.
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0057]")
  private Cluster betaetigung;

  /**
   * Path: Registereintrag/Koerpertemperatur/Beliebiges Ereignis/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Registereintrag/Koerpertemperatur/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Registereintrag/Koerpertemperatur/Strukturierte Lokalisation der Messung
   * Description: Strukturierte anatomische Lokalisation, an dem die Messung vorgenommen wurde.
   */
  @Path("/protocol[at0020]/items[at0064]")
  private List<Cluster> strukturierteLokalisationDerMessung;

  /**
   * Path: Registereintrag/Koerpertemperatur/Gerät
   * Description: Details über das Gerät, das zur Temperaturmessung benutzt wurde.
   */
  @Path("/protocol[at0020]/items[at0059]")
  private Cluster geraet;

  /**
   * Path: Registereintrag/Koerpertemperatur/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen.
   */
  @Path("/protocol[at0020]/items[at0062]")
  private List<Cluster> erweiterung;

  /**
   * Path: Registereintrag/Koerpertemperatur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Registereintrag/Koerpertemperatur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Registereintrag/Koerpertemperatur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTemperaturMagnitude(Double temperaturMagnitude) {
     this.temperaturMagnitude = temperaturMagnitude;
  }

  public Double getTemperaturMagnitude() {
     return this.temperaturMagnitude ;
  }

  public void setTemperaturUnits(String temperaturUnits) {
     this.temperaturUnits = temperaturUnits;
  }

  public String getTemperaturUnits() {
     return this.temperaturUnits ;
  }

  public void setTemperaturNullFlavourDefiningCode(NullFlavour temperaturNullFlavourDefiningCode) {
     this.temperaturNullFlavourDefiningCode = temperaturNullFlavourDefiningCode;
  }

  public NullFlavour getTemperaturNullFlavourDefiningCode() {
     return this.temperaturNullFlavourDefiningCode ;
  }

  public void setUmgebungsbedingungen(List<Cluster> umgebungsbedingungen) {
     this.umgebungsbedingungen = umgebungsbedingungen;
  }

  public List<Cluster> getUmgebungsbedingungen() {
     return this.umgebungsbedingungen ;
  }

  public void setBetaetigung(Cluster betaetigung) {
     this.betaetigung = betaetigung;
  }

  public Cluster getBetaetigung() {
     return this.betaetigung ;
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

  public void setStrukturierteLokalisationDerMessung(
      List<Cluster> strukturierteLokalisationDerMessung) {
     this.strukturierteLokalisationDerMessung = strukturierteLokalisationDerMessung;
  }

  public List<Cluster> getStrukturierteLokalisationDerMessung() {
     return this.strukturierteLokalisationDerMessung ;
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
