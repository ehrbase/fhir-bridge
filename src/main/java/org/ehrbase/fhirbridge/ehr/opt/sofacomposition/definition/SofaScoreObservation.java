package org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
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
    date = "2021-03-09T11:57:09.560007+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class SofaScoreObservation implements EntryEntity {
  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/Atemtätigkeit
   * Description: Die Atemtätigkeit als PaO2/FiO2 Ratio in mmHg zur Bewertung der Lungenfunktion.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value")
  private DvOrdinal atemtaetigkeit;

  /**
   * Path: SOFA-Score/SOFA-Score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Baum/Atemtätigkeit/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour atemtaetigkeitNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/Zentrales Nervensystem
   * Description: Die Bewertung des ZNS mit Glasgow Coma Score.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value")
  private DvOrdinal zentralesNervensystem;

  /**
   * Path: SOFA-Score/SOFA-Score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Baum/Zentrales Nervensystem/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour zentralesNervensystemNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/Herz-Kreislauf-System
   * Description: Mittlerer arterieller Druck (MAP) oder Verabreichung von Vasopressoren.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
  private DvOrdinal herzKreislaufSystem;

  /**
   * Path: SOFA-Score/SOFA-Score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Baum/Herz-Kreislauf-System/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/null_flavour|defining_code")
  private NullFlavour herzKreislaufSystemNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/Leberfunktion
   * Description: Der Bilirubin-Wert in Milligramm pro Deziliter [mg/dl].
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value")
  private DvOrdinal leberfunktion;

  /**
   * Path: SOFA-Score/SOFA-Score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Baum/Leberfunktion/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/null_flavour|defining_code")
  private NullFlavour leberfunktionNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/Blutgerinnung
   * Description: Die Thrombozytenzahl pro Mikroliter [10³/µl].
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value")
  private DvOrdinal blutgerinnung;

  /**
   * Path: SOFA-Score/SOFA-Score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Baum/Blutgerinnung/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/null_flavour|defining_code")
  private NullFlavour blutgerinnungNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/Nierenfunktion
   * Description: Der Kreatinin-Wert in Milligramm pro Deziliter [mg/dl].
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value")
  private DvOrdinal nierenfunktion;

  /**
   * Path: SOFA-Score/SOFA-Score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Baum/Nierenfunktion/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/null_flavour|defining_code")
  private NullFlavour nierenfunktionNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/SOFA-Score
   * Description: Der SOFA-Score ist die Summe der Punkte für die sechs Parameter.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value|magnitude")
  private Long sofaScoreMagnitude;

  /**
   * Path: SOFA-Score/SOFA-Score/Event Series/Beliebiges Ereignis zu einem Zeitpunkt/Baum/SOFA-Score/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0010]/null_flavour|defining_code")
  private NullFlavour sofaScoreNullFlavourDefiningCode;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/Baum
   * Description: @ internal @
   */
  @Path("/data[at0001]/events[at0002]/state[at0013]")
  private ItemTree baum;

  /**
   * Path: SOFA-Score/SOFA-Score/Beliebiges Ereignis zu einem Zeitpunkt/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: SOFA-Score/SOFA-Score/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: SOFA-Score/SOFA-Score/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0014]/items[at0015]")
  private List<Cluster> erweiterung;

  /**
   * Path: SOFA-Score/SOFA-Score/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: SOFA-Score/SOFA-Score/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: SOFA-Score/SOFA-Score/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAtemtaetigkeit(DvOrdinal atemtaetigkeit) {
     this.atemtaetigkeit = atemtaetigkeit;
  }

  public DvOrdinal getAtemtaetigkeit() {
     return this.atemtaetigkeit ;
  }

  public void setAtemtaetigkeitNullFlavourDefiningCode(
      NullFlavour atemtaetigkeitNullFlavourDefiningCode) {
     this.atemtaetigkeitNullFlavourDefiningCode = atemtaetigkeitNullFlavourDefiningCode;
  }

  public NullFlavour getAtemtaetigkeitNullFlavourDefiningCode() {
     return this.atemtaetigkeitNullFlavourDefiningCode ;
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

  public void setHerzKreislaufSystem(DvOrdinal herzKreislaufSystem) {
     this.herzKreislaufSystem = herzKreislaufSystem;
  }

  public DvOrdinal getHerzKreislaufSystem() {
     return this.herzKreislaufSystem ;
  }

  public void setHerzKreislaufSystemNullFlavourDefiningCode(
      NullFlavour herzKreislaufSystemNullFlavourDefiningCode) {
     this.herzKreislaufSystemNullFlavourDefiningCode = herzKreislaufSystemNullFlavourDefiningCode;
  }

  public NullFlavour getHerzKreislaufSystemNullFlavourDefiningCode() {
     return this.herzKreislaufSystemNullFlavourDefiningCode ;
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

  public void setSofaScoreMagnitude(Long sofaScoreMagnitude) {
     this.sofaScoreMagnitude = sofaScoreMagnitude;
  }

  public Long getSofaScoreMagnitude() {
     return this.sofaScoreMagnitude ;
  }

  public void setSofaScoreNullFlavourDefiningCode(NullFlavour sofaScoreNullFlavourDefiningCode) {
     this.sofaScoreNullFlavourDefiningCode = sofaScoreNullFlavourDefiningCode;
  }

  public NullFlavour getSofaScoreNullFlavourDefiningCode() {
     return this.sofaScoreNullFlavourDefiningCode ;
  }

  public void setBaum(ItemTree baum) {
     this.baum = baum;
  }

  public ItemTree getBaum() {
     return this.baum ;
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
