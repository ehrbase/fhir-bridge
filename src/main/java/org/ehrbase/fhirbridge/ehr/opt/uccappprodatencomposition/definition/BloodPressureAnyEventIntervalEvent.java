package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
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
    date = "2022-05-09T16:40:29.020335711+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("INTERVAL_EVENT")
public class BloodPressureAnyEventIntervalEvent implements IntervalEventEntity, BloodPressureAnyEventChoice {
  /**
   * Path: Self monitoring/Blood pressure/Any event/Systolic
   * Description: Peak systemic arterial blood pressure  - measured in systolic or contraction phase of the heart cycle.
   */
  @Path("/data[at0003]/items[at0004]/value|magnitude")
  private Double systolicMagnitude;

  /**
   * Path: Self monitoring/Blood pressure/Any event/Systolic
   * Description: Peak systemic arterial blood pressure  - measured in systolic or contraction phase of the heart cycle.
   */
  @Path("/data[at0003]/items[at0004]/value|units")
  private String systolicUnits;

  /**
   * Path: Self monitoring/Blood pressure/Any event/blood pressure/Systolic/null_flavour
   */
  @Path("/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour systolicNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Blood pressure/Any event/Diastolic
   * Description: Minimum systemic arterial blood pressure - measured in the diastolic or relaxation phase of the heart cycle.
   */
  @Path("/data[at0003]/items[at0005]/value|magnitude")
  private Double diastolicMagnitude;

  /**
   * Path: Self monitoring/Blood pressure/Any event/Diastolic
   * Description: Minimum systemic arterial blood pressure - measured in the diastolic or relaxation phase of the heart cycle.
   */
  @Path("/data[at0003]/items[at0005]/value|units")
  private String diastolicUnits;

  /**
   * Path: Self monitoring/Blood pressure/Any event/blood pressure/Diastolic/null_flavour
   */
  @Path("/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour diastolicNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Blood pressure/Any event/Exertion
   * Description: Details about physical activity undertaken at the time of blood pressure measurement.
   */
  @Path("/state[at0007]/items[at1030]")
  private Cluster exertion;

  /**
   * Path: Self monitoring/Blood pressure/Any event/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Self monitoring/Blood pressure/Any event/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Self monitoring/Blood pressure/Any event/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Path: Self monitoring/Blood pressure/Any event/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Path: Self monitoring/Blood pressure/Any event/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

  public void setSystolicMagnitude(Double systolicMagnitude) {
     this.systolicMagnitude = systolicMagnitude;
  }

  public Double getSystolicMagnitude() {
     return this.systolicMagnitude ;
  }

  public void setSystolicUnits(String systolicUnits) {
     this.systolicUnits = systolicUnits;
  }

  public String getSystolicUnits() {
     return this.systolicUnits ;
  }

  public void setSystolicNullFlavourDefiningCode(NullFlavour systolicNullFlavourDefiningCode) {
     this.systolicNullFlavourDefiningCode = systolicNullFlavourDefiningCode;
  }

  public NullFlavour getSystolicNullFlavourDefiningCode() {
     return this.systolicNullFlavourDefiningCode ;
  }

  public void setDiastolicMagnitude(Double diastolicMagnitude) {
     this.diastolicMagnitude = diastolicMagnitude;
  }

  public Double getDiastolicMagnitude() {
     return this.diastolicMagnitude ;
  }

  public void setDiastolicUnits(String diastolicUnits) {
     this.diastolicUnits = diastolicUnits;
  }

  public String getDiastolicUnits() {
     return this.diastolicUnits ;
  }

  public void setDiastolicNullFlavourDefiningCode(NullFlavour diastolicNullFlavourDefiningCode) {
     this.diastolicNullFlavourDefiningCode = diastolicNullFlavourDefiningCode;
  }

  public NullFlavour getDiastolicNullFlavourDefiningCode() {
     return this.diastolicNullFlavourDefiningCode ;
  }

  public void setExertion(Cluster exertion) {
     this.exertion = exertion;
  }

  public Cluster getExertion() {
     return this.exertion ;
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
