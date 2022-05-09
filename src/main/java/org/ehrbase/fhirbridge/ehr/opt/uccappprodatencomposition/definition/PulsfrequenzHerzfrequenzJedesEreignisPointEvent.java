package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:01:54.599288220+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("POINT_EVENT")
public class PulsfrequenzHerzfrequenzJedesEreignisPointEvent implements PointEventEntity, PulsfrequenzHerzfrequenzJedesEreignisChoice {
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
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Belastungsgrad
   * Description: Aufzeichnung von Informationen zum Belastungsgrad/Zustand des Patienten.
   */
  @Path("/state[at0012]/items[openEHR-EHR-CLUSTER.level_of_exertion.v0]")
  private List<BelastungsgradCluster2> belastungsgrad;

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
