package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Item;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.Long;
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
@Archetype("openEHR-EHR-OBSERVATION.wearable_sensor_activity.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-04-13T16:38:21.377964402+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class MitSensorGemesseneKoerperlicheAktivitaetObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Anzahl der zurückgelegten Schritte
   * Description: Anzahl der Schritte der Person, welche diese im Zeitraum der Messung zurückgelegt hat.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0016]/value|magnitude")
  private Long anzahlDerZurueckgelegtenSchritteMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Event Series/Jedes Ereignis/Baum/Anzahl der zurückgelegten Schritte/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0016]/null_flavour|defining_code")
  private NullFlavour anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Zurückgelegte Distanz
   * Description: Distanz, die von der Person zurückgelegt wurde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|magnitude")
  private Double zurueckgelegteDistanzMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Zurückgelegte Distanz
   * Description: Distanz, die von der Person zurückgelegt wurde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|units")
  private String zurueckgelegteDistanzUnits;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Event Series/Jedes Ereignis/Baum/Zurückgelegte Distanz/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0017]/null_flavour|defining_code")
  private NullFlavour zurueckgelegteDistanzNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Anzahl Stockwerke
   * Description: Anzahl der gestiegenen Stockwerke.
   * Comment: Im Regelfall werden nur hochgestiegene Stockwerke gemessen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0020]/value|magnitude")
  private Long anzahlStockwerkeMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Event Series/Jedes Ereignis/Baum/Anzahl Stockwerke/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0020]/null_flavour|defining_code")
  private NullFlavour anzahlStockwerkeNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Höhe
   * Description: Slot um Archetypen einzubinden, die Messungen der Höhe über dem Meeresspiegel beschreiben oder Veränderungen der Höhe in der sich die Person bewegt.
   * Comment: In diesem Slot können Archetypen eingehängt werden, die entweder zusammengefasste Höhenparameter abbilden oder den Archetypen um Reihenmessungen der momentanen Höhe erweitern.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0062]")
  private List<Item> hoehe;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Ruheenergie
   * Description: Menge an Energie, die von der Person im Ruhezustand verbraucht wird. Wird auch als Grundumsatz bezeichnet. Zusammen mit der Aktivitätsenergie ergibt sich der gesamte Energieverbrauch des Körpers.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0023]/value|magnitude")
  private Double ruheenergieMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Ruheenergie
   * Description: Menge an Energie, die von der Person im Ruhezustand verbraucht wird. Wird auch als Grundumsatz bezeichnet. Zusammen mit der Aktivitätsenergie ergibt sich der gesamte Energieverbrauch des Körpers.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0023]/value|units")
  private String ruheenergieUnits;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Event Series/Jedes Ereignis/Baum/Ruheenergie/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0023]/null_flavour|defining_code")
  private NullFlavour ruheenergieNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Aktivitätsenergie
   * Description: Menge an Energie, die durch die Aktivität verbraucht und vom Sensor festgehalten wurde. Auch als Energieumsatz bezeichnet wird sie von der Ruheenergie (Grundumsatz) unterschieden. Ruhe- und Aktivitätsenergie zusammen ergeben den Gesamtenergieverbrauch des Körpers.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value|magnitude")
  private Double aktivitaetsenergieMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Aktivitätsenergie
   * Description: Menge an Energie, die durch die Aktivität verbraucht und vom Sensor festgehalten wurde. Auch als Energieumsatz bezeichnet wird sie von der Ruheenergie (Grundumsatz) unterschieden. Ruhe- und Aktivitätsenergie zusammen ergeben den Gesamtenergieverbrauch des Körpers.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value|units")
  private String aktivitaetsenergieUnits;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Event Series/Jedes Ereignis/Baum/Aktivitätsenergie/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0019]/null_flavour|defining_code")
  private NullFlavour aktivitaetsenergieNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Geschwindigkeit
   * Description: Slot um Archetypen einzubinden, um Messungen der Geschwindigkeit einzubinden.
   * Comment: In diesem Slot können Archetypen eingehängt werden, die entweder zusammengefasste Geschwindigkeitsparameter abbilden oder den Archetypen um Reihenmessungen der momentanen Geschwindigkeit erweitern.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0063]")
  private List<Item> geschwindigkeit;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Pace
   * Description: Slot um Archetypen einzubinden, die Pace beschreiben, die Zeit, in der eine bestimmte Distanz zurückgelegt wird. Kehrwert der Geschwindigkeit.
   * Comment: In diesem Slot können Archetypen eingehängt werden, die entweder zusammengefasste Pace Parameter abbilden oder den Archetypen um Reihenmessungen des momentanen Pace erweitern.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0064]")
  private List<Item> pace;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Aktivität
   * Description: Von der Person durchgeführte Aktivität.
   */
  @Path("/data[at0001]/events[at0002]/state[at0027]/items[at0028]/value|defining_code")
  private AktivitaetDefiningCode aktivitaetDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Event Series/Jedes Ereignis/Baum/Aktivität/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/state[at0027]/items[at0028]/null_flavour|defining_code")
  private NullFlavour aktivitaetNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

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

  public void setAnzahlDerZurueckgelegtenSchritteMagnitude(
      Long anzahlDerZurueckgelegtenSchritteMagnitude) {
     this.anzahlDerZurueckgelegtenSchritteMagnitude = anzahlDerZurueckgelegtenSchritteMagnitude;
  }

  public Long getAnzahlDerZurueckgelegtenSchritteMagnitude() {
     return this.anzahlDerZurueckgelegtenSchritteMagnitude ;
  }

  public void setAnzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode(
      NullFlavour anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode) {
     this.anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode = anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode;
  }

  public NullFlavour getAnzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode() {
     return this.anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode ;
  }

  public void setZurueckgelegteDistanzMagnitude(Double zurueckgelegteDistanzMagnitude) {
     this.zurueckgelegteDistanzMagnitude = zurueckgelegteDistanzMagnitude;
  }

  public Double getZurueckgelegteDistanzMagnitude() {
     return this.zurueckgelegteDistanzMagnitude ;
  }

  public void setZurueckgelegteDistanzUnits(String zurueckgelegteDistanzUnits) {
     this.zurueckgelegteDistanzUnits = zurueckgelegteDistanzUnits;
  }

  public String getZurueckgelegteDistanzUnits() {
     return this.zurueckgelegteDistanzUnits ;
  }

  public void setZurueckgelegteDistanzNullFlavourDefiningCode(
      NullFlavour zurueckgelegteDistanzNullFlavourDefiningCode) {
     this.zurueckgelegteDistanzNullFlavourDefiningCode = zurueckgelegteDistanzNullFlavourDefiningCode;
  }

  public NullFlavour getZurueckgelegteDistanzNullFlavourDefiningCode() {
     return this.zurueckgelegteDistanzNullFlavourDefiningCode ;
  }

  public void setAnzahlStockwerkeMagnitude(Long anzahlStockwerkeMagnitude) {
     this.anzahlStockwerkeMagnitude = anzahlStockwerkeMagnitude;
  }

  public Long getAnzahlStockwerkeMagnitude() {
     return this.anzahlStockwerkeMagnitude ;
  }

  public void setAnzahlStockwerkeNullFlavourDefiningCode(
      NullFlavour anzahlStockwerkeNullFlavourDefiningCode) {
     this.anzahlStockwerkeNullFlavourDefiningCode = anzahlStockwerkeNullFlavourDefiningCode;
  }

  public NullFlavour getAnzahlStockwerkeNullFlavourDefiningCode() {
     return this.anzahlStockwerkeNullFlavourDefiningCode ;
  }

  public void setHoehe(List<Item> hoehe) {
     this.hoehe = hoehe;
  }

  public List<Item> getHoehe() {
     return this.hoehe ;
  }

  public void setRuheenergieMagnitude(Double ruheenergieMagnitude) {
     this.ruheenergieMagnitude = ruheenergieMagnitude;
  }

  public Double getRuheenergieMagnitude() {
     return this.ruheenergieMagnitude ;
  }

  public void setRuheenergieUnits(String ruheenergieUnits) {
     this.ruheenergieUnits = ruheenergieUnits;
  }

  public String getRuheenergieUnits() {
     return this.ruheenergieUnits ;
  }

  public void setRuheenergieNullFlavourDefiningCode(
      NullFlavour ruheenergieNullFlavourDefiningCode) {
     this.ruheenergieNullFlavourDefiningCode = ruheenergieNullFlavourDefiningCode;
  }

  public NullFlavour getRuheenergieNullFlavourDefiningCode() {
     return this.ruheenergieNullFlavourDefiningCode ;
  }

  public void setAktivitaetsenergieMagnitude(Double aktivitaetsenergieMagnitude) {
     this.aktivitaetsenergieMagnitude = aktivitaetsenergieMagnitude;
  }

  public Double getAktivitaetsenergieMagnitude() {
     return this.aktivitaetsenergieMagnitude ;
  }

  public void setAktivitaetsenergieUnits(String aktivitaetsenergieUnits) {
     this.aktivitaetsenergieUnits = aktivitaetsenergieUnits;
  }

  public String getAktivitaetsenergieUnits() {
     return this.aktivitaetsenergieUnits ;
  }

  public void setAktivitaetsenergieNullFlavourDefiningCode(
      NullFlavour aktivitaetsenergieNullFlavourDefiningCode) {
     this.aktivitaetsenergieNullFlavourDefiningCode = aktivitaetsenergieNullFlavourDefiningCode;
  }

  public NullFlavour getAktivitaetsenergieNullFlavourDefiningCode() {
     return this.aktivitaetsenergieNullFlavourDefiningCode ;
  }

  public void setGeschwindigkeit(List<Item> geschwindigkeit) {
     this.geschwindigkeit = geschwindigkeit;
  }

  public List<Item> getGeschwindigkeit() {
     return this.geschwindigkeit ;
  }

  public void setPace(List<Item> pace) {
     this.pace = pace;
  }

  public List<Item> getPace() {
     return this.pace ;
  }

  public void setAktivitaetDefiningCode(AktivitaetDefiningCode aktivitaetDefiningCode) {
     this.aktivitaetDefiningCode = aktivitaetDefiningCode;
  }

  public AktivitaetDefiningCode getAktivitaetDefiningCode() {
     return this.aktivitaetDefiningCode ;
  }

  public void setAktivitaetNullFlavourDefiningCode(NullFlavour aktivitaetNullFlavourDefiningCode) {
     this.aktivitaetNullFlavourDefiningCode = aktivitaetNullFlavourDefiningCode;
  }

  public NullFlavour getAktivitaetNullFlavourDefiningCode() {
     return this.aktivitaetNullFlavourDefiningCode ;
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
}
