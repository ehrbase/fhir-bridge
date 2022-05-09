package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T16:40:29.004724839+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class BloodPressure24HourAverageIntervalEvent implements IntervalEventEntity {
  /**
   * Path: Self monitoring/Blood pressure/24 hour average/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Path: Self monitoring/Blood pressure/24 hour average/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Path: Self monitoring/Blood pressure/24 hour average/Clinical interpretation
   * Description: Single word, phrase or brief description that represents the clinical meaning and significance of the blood pressure measurement.
   */
  @Path("/data[at0003]/items[at1059]/value|value")
  private String clinicalInterpretationValue;

  /**
   * Path: Self monitoring/Blood pressure/History/24 hour average/blood pressure/Clinical interpretation/null_flavour
   */
  @Path("/data[at0003]/items[at1059]/null_flavour|defining_code")
  private NullFlavour clinicalInterpretationNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Blood pressure/24 hour average/Level of exertion
   * Description: Record information about level of exertion.
   */
  @Path("/state[at0007]/items[openEHR-EHR-CLUSTER.level_of_exertion.v0]")
  private LevelOfExertionCluster levelOfExertion;

  /**
   * Path: Self monitoring/Blood pressure/24 hour average/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

  /**
   * Path: Self monitoring/Blood pressure/24 hour average/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Self monitoring/Blood pressure/24 hour average/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
     this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
     return this.mathFunctionDefiningCode ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setClinicalInterpretationValue(String clinicalInterpretationValue) {
     this.clinicalInterpretationValue = clinicalInterpretationValue;
  }

  public String getClinicalInterpretationValue() {
     return this.clinicalInterpretationValue ;
  }

  public void setClinicalInterpretationNullFlavourDefiningCode(
      NullFlavour clinicalInterpretationNullFlavourDefiningCode) {
     this.clinicalInterpretationNullFlavourDefiningCode = clinicalInterpretationNullFlavourDefiningCode;
  }

  public NullFlavour getClinicalInterpretationNullFlavourDefiningCode() {
     return this.clinicalInterpretationNullFlavourDefiningCode ;
  }

  public void setLevelOfExertion(LevelOfExertionCluster levelOfExertion) {
     this.levelOfExertion = levelOfExertion;
  }

  public LevelOfExertionCluster getLevelOfExertion() {
     return this.levelOfExertion ;
  }

  public void setSampleCount(Long sampleCount) {
     this.sampleCount = sampleCount;
  }

  public Long getSampleCount() {
     return this.sampleCount ;
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
}
