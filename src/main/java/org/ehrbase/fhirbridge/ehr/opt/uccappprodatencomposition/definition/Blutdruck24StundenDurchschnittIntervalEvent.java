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
    date = "2022-05-10T17:43:37.117860953+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class Blutdruck24StundenDurchschnittIntervalEvent implements IntervalEventEntity {
  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt/Klinische Interpretation
   * Description: Einzelnes Wort, Ausdruck oder Kurzbeschreibung der klinischen Bedeutung und Aussagekraft der Blutdruckmessung.
   */
  @Path("/data[at0003]/items[at1059]/value|value")
  private String klinischeInterpretationValue;

  /**
   * Path: Selbstüberwachung/Blutdruck/Historie/24 Stunden Durchschnitt/Blutdruck/Klinische Interpretation/null_flavour
   */
  @Path("/data[at0003]/items[at1059]/null_flavour|defining_code")
  private NullFlavour klinischeInterpretationNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt/Belastungsgrad
   * Description: Aufzeichnung von Informationen zum Belastungsgrad/Zustand des Patienten.
   */
  @Path("/state[at0007]/items[openEHR-EHR-CLUSTER.level_of_exertion.v0]")
  private BelastungsgradCluster belastungsgrad;

  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt/time
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

  public void setKlinischeInterpretationValue(String klinischeInterpretationValue) {
     this.klinischeInterpretationValue = klinischeInterpretationValue;
  }

  public String getKlinischeInterpretationValue() {
     return this.klinischeInterpretationValue ;
  }

  public void setKlinischeInterpretationNullFlavourDefiningCode(
      NullFlavour klinischeInterpretationNullFlavourDefiningCode) {
     this.klinischeInterpretationNullFlavourDefiningCode = klinischeInterpretationNullFlavourDefiningCode;
  }

  public NullFlavour getKlinischeInterpretationNullFlavourDefiningCode() {
     return this.klinischeInterpretationNullFlavourDefiningCode ;
  }

  public void setBelastungsgrad(BelastungsgradCluster belastungsgrad) {
     this.belastungsgrad = belastungsgrad;
  }

  public BelastungsgradCluster getBelastungsgrad() {
     return this.belastungsgrad ;
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
