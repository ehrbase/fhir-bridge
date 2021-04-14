package org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Long;
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
@Archetype("openEHR-EHR-OBSERVATION.sofa_score.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-01T12:01:13.818668+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class SofaScoreObservation implements EntryEntity {
  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/Respiration
   * Description: Das Verhältnis zwischen dem Sauerstoffpartialdruck (PaO₂) und der Fraktion des eingeatmeten Sauerstoffs (FiO₂) ist ein Indikator für eine mögliche Funktionsstörung des Atmungssystems.
   * Comment: Das Verhältnis PaO₂/FiO₂ wird an bestimmten Orten in kPa gemessen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value")
  private DvOrdinal respiration;

  /**
   * Path: SOFA-Score/SOFA score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Tree/Respiration/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour respirationNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/Kardiovaskuläres System
   * Description: Der mittlere arterielle Druck (MAD) oder der Bedarf an Vasopressoren (VP), (Dopamin (DA), Adrenalin (A), Noradrenalin (NA) oder Dobutamin) sind Indikatoren für eine mögliche Dysfunktion des Herz-Kreislauf-Systems.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value")
  private DvOrdinal kardiovaskulaeresSystem;

  /**
   * Path: SOFA-Score/SOFA score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Tree/Kardiovaskuläres System/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0010]/null_flavour|defining_code")
  private NullFlavour kardiovaskulaeresSystemNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/Zentrales Nervensystem
   * Description: Die Glasgow Coma Scale (GCS) ist ein Indikator für eine mögliche Funktionsstörung des zentralen Nervensystems.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0016]/value")
  private DvOrdinal zentralesNervensystem;

  /**
   * Path: SOFA-Score/SOFA score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Tree/Zentrales Nervensystem/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0016]/null_flavour|defining_code")
  private NullFlavour zentralesNervensystemNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/Nierenfunktion
   * Description: Die Kreatininkonzentration und die 24-h-Urinausscheidung (UOP) sind Indikatoren für eine mögliche Funktionsstörung des zentralen Nervensystems.
   * Comment: An einigen Orten wird die Kreatinin-Konzentration in μmol/L gemessen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/value")
  private DvOrdinal nierenfunktion;

  /**
   * Path: SOFA-Score/SOFA score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Tree/Nierenfunktion/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/null_flavour|defining_code")
  private NullFlavour nierenfunktionNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/Leberfunktion
   * Description: Die Bilirubin-Konzentration ist ein Indikator für eine mögliche Funktionsstörung des zentralen Nervensystems.
   * Comment: In manchen Orten wird die Bilirubin-Konzentration in μmol/L gemessen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value")
  private DvOrdinal leberfunktion;

  /**
   * Path: SOFA-Score/SOFA score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Tree/Leberfunktion/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0028]/null_flavour|defining_code")
  private NullFlavour leberfunktionNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/Blutgerinnung
   * Description: Die Thrombozytenkonzentration ist ein Indikator für eine mögliche Funktionsstörung des Blutgerinnungssystems.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0034]/value")
  private DvOrdinal blutgerinnung;

  /**
   * Path: SOFA-Score/SOFA score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Tree/Blutgerinnung/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0034]/null_flavour|defining_code")
  private NullFlavour blutgerinnungNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/Gesamtergebnis
   * Description: Die Gesamtanzahl der einzelnen Komponentenparameter für den SOFA-Score.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0041]/value|magnitude")
  private Long gesamtergebnisMagnitude;

  /**
   * Path: SOFA-Score/SOFA score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Tree/Gesamtergebnis/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0041]/null_flavour|defining_code")
  private NullFlavour gesamtergebnisNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA score/Beliebiges Ereignis zu einem Zeitpunkt/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: SOFA-Score/SOFA score/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: SOFA-Score/SOFA score/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0043]/items[at0044]")
  private List<Cluster> erweiterung;

  /**
   * Path: SOFA-Score/SOFA score/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: SOFA-Score/SOFA score/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: SOFA-Score/SOFA score/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRespiration(DvOrdinal respiration) {
     this.respiration = respiration;
  }

  public DvOrdinal getRespiration() {
     return this.respiration ;
  }

  public void setRespirationNullFlavourDefiningCode(
      NullFlavour respirationNullFlavourDefiningCode) {
     this.respirationNullFlavourDefiningCode = respirationNullFlavourDefiningCode;
  }

  public NullFlavour getRespirationNullFlavourDefiningCode() {
     return this.respirationNullFlavourDefiningCode ;
  }

  public void setKardiovaskulaeresSystem(DvOrdinal kardiovaskulaeresSystem) {
     this.kardiovaskulaeresSystem = kardiovaskulaeresSystem;
  }

  public DvOrdinal getKardiovaskulaeresSystem() {
     return this.kardiovaskulaeresSystem ;
  }

  public void setKardiovaskulaeresSystemNullFlavourDefiningCode(
      NullFlavour kardiovaskulaeresSystemNullFlavourDefiningCode) {
     this.kardiovaskulaeresSystemNullFlavourDefiningCode = kardiovaskulaeresSystemNullFlavourDefiningCode;
  }

  public NullFlavour getKardiovaskulaeresSystemNullFlavourDefiningCode() {
     return this.kardiovaskulaeresSystemNullFlavourDefiningCode ;
  }

  public void setZentralesNervensystem(DvOrdinal zentralesNervensystem) {
     this.zentralesNervensystem = zentralesNervensystem;
  }

  public DvOrdinal getZentralesNervensystem() {
     return this.zentralesNervensystem ;
  }

  public void setZentralesNervensystemNullFlavourDefiningCode(
      NullFlavour zentralesNervensystemNullFlavourDefiningCode) {
     this.zentralesNervensystemNullFlavourDefiningCode = zentralesNervensystemNullFlavourDefiningCode;
  }

  public NullFlavour getZentralesNervensystemNullFlavourDefiningCode() {
     return this.zentralesNervensystemNullFlavourDefiningCode ;
  }

  public void setNierenfunktion(DvOrdinal nierenfunktion) {
     this.nierenfunktion = nierenfunktion;
  }

  public DvOrdinal getNierenfunktion() {
     return this.nierenfunktion ;
  }

  public void setNierenfunktionNullFlavourDefiningCode(
      NullFlavour nierenfunktionNullFlavourDefiningCode) {
     this.nierenfunktionNullFlavourDefiningCode = nierenfunktionNullFlavourDefiningCode;
  }

  public NullFlavour getNierenfunktionNullFlavourDefiningCode() {
     return this.nierenfunktionNullFlavourDefiningCode ;
  }

  public void setLeberfunktion(DvOrdinal leberfunktion) {
     this.leberfunktion = leberfunktion;
  }

  public DvOrdinal getLeberfunktion() {
     return this.leberfunktion ;
  }

  public void setLeberfunktionNullFlavourDefiningCode(
      NullFlavour leberfunktionNullFlavourDefiningCode) {
     this.leberfunktionNullFlavourDefiningCode = leberfunktionNullFlavourDefiningCode;
  }

  public NullFlavour getLeberfunktionNullFlavourDefiningCode() {
     return this.leberfunktionNullFlavourDefiningCode ;
  }

  public void setBlutgerinnung(DvOrdinal blutgerinnung) {
     this.blutgerinnung = blutgerinnung;
  }

  public DvOrdinal getBlutgerinnung() {
     return this.blutgerinnung ;
  }

  public void setBlutgerinnungNullFlavourDefiningCode(
      NullFlavour blutgerinnungNullFlavourDefiningCode) {
     this.blutgerinnungNullFlavourDefiningCode = blutgerinnungNullFlavourDefiningCode;
  }

  public NullFlavour getBlutgerinnungNullFlavourDefiningCode() {
     return this.blutgerinnungNullFlavourDefiningCode ;
  }

  public void setGesamtergebnisMagnitude(Long gesamtergebnisMagnitude) {
     this.gesamtergebnisMagnitude = gesamtergebnisMagnitude;
  }

  public Long getGesamtergebnisMagnitude() {
     return this.gesamtergebnisMagnitude ;
  }

  public void setGesamtergebnisNullFlavourDefiningCode(
      NullFlavour gesamtergebnisNullFlavourDefiningCode) {
     this.gesamtergebnisNullFlavourDefiningCode = gesamtergebnisNullFlavourDefiningCode;
  }

  public NullFlavour getGesamtergebnisNullFlavourDefiningCode() {
     return this.gesamtergebnisNullFlavourDefiningCode ;
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
