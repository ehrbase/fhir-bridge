package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
    date = "2022-04-13T16:38:21.341631470+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("INTERVAL_EVENT")
public class PulsfrequenzHerzfrequenzJedesEreignisIntervalEvent implements IntervalEventEntity, PulsfrequenzHerzfrequenzJedesEreignisChoice {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double frequenzMagnitude;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0001]/items[at0004]/value|units")
  private String frequenzUnits;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Frequenz/null_flavour
   */
  @Path("/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour frequenzNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Regelmäßigkeit
   * Description: Regelmäßigkeit der Puls-/Herzfrequenz.
   */
  @Path("/data[at0001]/items[at0005]/value|defining_code")
  private RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Regelmäßigkeit/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour regelmaessigkeitNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Unregelmäßiger Typ
   * Description: Ein spezifischeres Verlaufsmuster einer unregelmäßigen Puls- oder Herzfrequenz.
   * Comment: Die Auswahl eines Wertes dieser Wertemenge ist nur valide, wenn "Unregelmäßig" vom Datenelement "Regelmäßigkeit" angegeben wurde.
   */
  @Path("/data[at0001]/items[at1055]/value|defining_code")
  private UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Unregelmäßiger Typ/null_flavour
   */
  @Path("/data[at0001]/items[at1055]/null_flavour|defining_code")
  private NullFlavour unregelmaessigerTypNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Anstrengung
   * Description: Details über die körperliche Anstrengung, die der Patient während der Untersuchung ausgesetzt war.
   */
  @Path("/state[at0012]/items[at1017]")
  private List<Cluster> anstrengung;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

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

  public void setRegelmaessigkeitDefiningCode(
      RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode) {
     this.regelmaessigkeitDefiningCode = regelmaessigkeitDefiningCode;
  }

  public RegelmaessigkeitDefiningCode getRegelmaessigkeitDefiningCode() {
     return this.regelmaessigkeitDefiningCode ;
  }

  public void setRegelmaessigkeitNullFlavourDefiningCode(
      NullFlavour regelmaessigkeitNullFlavourDefiningCode) {
     this.regelmaessigkeitNullFlavourDefiningCode = regelmaessigkeitNullFlavourDefiningCode;
  }

  public NullFlavour getRegelmaessigkeitNullFlavourDefiningCode() {
     return this.regelmaessigkeitNullFlavourDefiningCode ;
  }

  public void setUnregelmaessigerTypDefiningCode(
      UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode) {
     this.unregelmaessigerTypDefiningCode = unregelmaessigerTypDefiningCode;
  }

  public UnregelmaessigerTypDefiningCode getUnregelmaessigerTypDefiningCode() {
     return this.unregelmaessigerTypDefiningCode ;
  }

  public void setUnregelmaessigerTypNullFlavourDefiningCode(
      NullFlavour unregelmaessigerTypNullFlavourDefiningCode) {
     this.unregelmaessigerTypNullFlavourDefiningCode = unregelmaessigerTypNullFlavourDefiningCode;
  }

  public NullFlavour getUnregelmaessigerTypNullFlavourDefiningCode() {
     return this.unregelmaessigerTypNullFlavourDefiningCode ;
  }

  public void setAnstrengung(List<Cluster> anstrengung) {
     this.anstrengung = anstrengung;
  }

  public List<Cluster> getAnstrengung() {
     return this.anstrengung ;
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
