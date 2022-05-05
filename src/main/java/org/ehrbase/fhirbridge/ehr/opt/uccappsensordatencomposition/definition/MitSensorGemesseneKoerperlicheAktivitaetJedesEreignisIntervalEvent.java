package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Item;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-05T11:59:39.292078+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("INTERVAL_EVENT")
public class MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisIntervalEvent implements IntervalEventEntity, MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice {
  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Anzahl der zurückgelegten Schritte
   * Description: Anzahl der Schritte der Person, welche diese im Zeitraum der Messung zurückgelegt hat.
   */
  @Path("/data[at0003]/items[at0016]/value|magnitude")
  private Long anzahlDerZurueckgelegtenSchritteMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Baum/Anzahl der zurückgelegten Schritte/null_flavour
   */
  @Path("/data[at0003]/items[at0016]/null_flavour|defining_code")
  private NullFlavour anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Zurückgelegte Distanz
   * Description: Distanz, die von der Person zurückgelegt wurde.
   */
  @Path("/data[at0003]/items[at0017]/value|magnitude")
  private Double zurueckgelegteDistanzMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Zurückgelegte Distanz
   * Description: Distanz, die von der Person zurückgelegt wurde.
   */
  @Path("/data[at0003]/items[at0017]/value|units")
  private String zurueckgelegteDistanzUnits;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Baum/Zurückgelegte Distanz/null_flavour
   */
  @Path("/data[at0003]/items[at0017]/null_flavour|defining_code")
  private NullFlavour zurueckgelegteDistanzNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Anzahl Stockwerke
   * Description: Anzahl der gestiegenen Stockwerke.
   * Comment: Im Regelfall werden nur hochgestiegene Stockwerke gemessen.
   */
  @Path("/data[at0003]/items[at0020]/value|magnitude")
  private Long anzahlStockwerkeMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Baum/Anzahl Stockwerke/null_flavour
   */
  @Path("/data[at0003]/items[at0020]/null_flavour|defining_code")
  private NullFlavour anzahlStockwerkeNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Höhe
   * Description: Slot um Archetypen einzubinden, die Messungen der Höhe über dem Meeresspiegel beschreiben oder Veränderungen der Höhe in der sich die Person bewegt.
   * Comment: In diesem Slot können Archetypen eingehängt werden, die entweder zusammengefasste Höhenparameter abbilden oder den Archetypen um Reihenmessungen der momentanen Höhe erweitern.
   */
  @Path("/data[at0003]/items[at0062]")
  private List<Item> hoehe;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Ruheenergie
   * Description: Menge an Energie, die von der Person im Ruhezustand verbraucht wird. Wird auch als Grundumsatz bezeichnet. Zusammen mit der Aktivitätsenergie ergibt sich der gesamte Energieverbrauch des Körpers.
   */
  @Path("/data[at0003]/items[at0023]/value|magnitude")
  private Double ruheenergieMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Ruheenergie
   * Description: Menge an Energie, die von der Person im Ruhezustand verbraucht wird. Wird auch als Grundumsatz bezeichnet. Zusammen mit der Aktivitätsenergie ergibt sich der gesamte Energieverbrauch des Körpers.
   */
  @Path("/data[at0003]/items[at0023]/value|units")
  private String ruheenergieUnits;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Baum/Ruheenergie/null_flavour
   */
  @Path("/data[at0003]/items[at0023]/null_flavour|defining_code")
  private NullFlavour ruheenergieNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Aktivitätsenergie
   * Description: Menge an Energie, die durch die Aktivität verbraucht und vom Sensor festgehalten wurde. Auch als Energieumsatz bezeichnet wird sie von der Ruheenergie (Grundumsatz) unterschieden. Ruhe- und Aktivitätsenergie zusammen ergeben den Gesamtenergieverbrauch des Körpers.
   */
  @Path("/data[at0003]/items[at0019]/value|magnitude")
  private Double aktivitaetsenergieMagnitude;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Aktivitätsenergie
   * Description: Menge an Energie, die durch die Aktivität verbraucht und vom Sensor festgehalten wurde. Auch als Energieumsatz bezeichnet wird sie von der Ruheenergie (Grundumsatz) unterschieden. Ruhe- und Aktivitätsenergie zusammen ergeben den Gesamtenergieverbrauch des Körpers.
   */
  @Path("/data[at0003]/items[at0019]/value|units")
  private String aktivitaetsenergieUnits;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Baum/Aktivitätsenergie/null_flavour
   */
  @Path("/data[at0003]/items[at0019]/null_flavour|defining_code")
  private NullFlavour aktivitaetsenergieNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Geschwindigkeit
   * Description: Slot um Archetypen einzubinden, um Messungen der Geschwindigkeit einzubinden.
   * Comment: In diesem Slot können Archetypen eingehängt werden, die entweder zusammengefasste Geschwindigkeitsparameter abbilden oder den Archetypen um Reihenmessungen der momentanen Geschwindigkeit erweitern.
   */
  @Path("/data[at0003]/items[at0063]")
  private List<Item> geschwindigkeit;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Pace
   * Description: Slot um Archetypen einzubinden, die Pace beschreiben, die Zeit, in der eine bestimmte Distanz zurückgelegt wird. Kehrwert der Geschwindigkeit.
   * Comment: In diesem Slot können Archetypen eingehängt werden, die entweder zusammengefasste Pace Parameter abbilden oder den Archetypen um Reihenmessungen des momentanen Pace erweitern.
   */
  @Path("/data[at0003]/items[at0064]")
  private List<Item> pace;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Aktivität
   * Description: Von der Person durchgeführte Aktivität.
   */
  @Path("/state[at0027]/items[at0028]/value|value")
  private String aktivitaetValue;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/Baum/Aktivität/null_flavour
   */
  @Path("/state[at0027]/items[at0028]/null_flavour|defining_code")
  private NullFlavour aktivitaetNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität/Jedes Ereignis/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

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

  public void setAktivitaetValue(String aktivitaetValue) {
     this.aktivitaetValue = aktivitaetValue;
  }

  public String getAktivitaetValue() {
     return this.aktivitaetValue ;
  }

  public void setAktivitaetNullFlavourDefiningCode(NullFlavour aktivitaetNullFlavourDefiningCode) {
     this.aktivitaetNullFlavourDefiningCode = aktivitaetNullFlavourDefiningCode;
  }

  public NullFlavour getAktivitaetNullFlavourDefiningCode() {
     return this.aktivitaetNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
     this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
     return this.mathFunctionDefiningCode ;
  }

  public void setSampleCount(Long sampleCount) {
     this.sampleCount = sampleCount;
  }

  public Long getSampleCount() {
     return this.sampleCount ;
  }
}
