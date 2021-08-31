package org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.pulse.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-31T12:18:54.519980500+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PulseHeartBeatObservation implements EntryEntity {
  /**
   * Path: Register entry/Pulse/Heart beat/Any event/Rate
   * Description: The rate of the pulse or heart beat, measured in beats per minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double rateMagnitude;

  /**
   * Path: Register entry/Pulse/Heart beat/Any event/Rate
   * Description: The rate of the pulse or heart beat, measured in beats per minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String rateUnits;

  /**
   * Path: Register entry/Pulse/Heart beat/history/Any event/Structure/Rate/null_flavour
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour rateNullFlavourDefiningCode;

  /**
   * Path: Register entry/Pulse/Heart beat/Any event/Exertion
   * Description: Details about physical exertion being undertaken during the examination.
   */
  @Path("/data[at0002]/events[at0003]/state[at0012]/items[at1017]")
  private List<Cluster> exertion;

  /**
   * Path: Register entry/Pulse/Heart beat/Any event/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Register entry/Pulse/Heart beat/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Register entry/Pulse/Heart beat/Device
   * Description: Details about the device used to measure the pulse rate or heart rate.
   */
  @Path("/protocol[at0010]/items[at1013]")
  private Cluster device;

  /**
   * Path: Register entry/Pulse/Heart beat/Extension
   * Description: Additional information required to capture local content or to align with other reference models/formalisms.
   * Comment: For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> extension;

  /**
   * Path: Register entry/Pulse/Heart beat/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Register entry/Pulse/Heart beat/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Register entry/Pulse/Heart beat/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRateMagnitude(Double rateMagnitude) {
     this.rateMagnitude = rateMagnitude;
  }

  public Double getRateMagnitude() {
     return this.rateMagnitude ;
  }

  public void setRateUnits(String rateUnits) {
     this.rateUnits = rateUnits;
  }

  public String getRateUnits() {
     return this.rateUnits ;
  }

  public void setRateNullFlavourDefiningCode(NullFlavour rateNullFlavourDefiningCode) {
     this.rateNullFlavourDefiningCode = rateNullFlavourDefiningCode;
  }

  public NullFlavour getRateNullFlavourDefiningCode() {
     return this.rateNullFlavourDefiningCode ;
  }

  public void setExertion(List<Cluster> exertion) {
     this.exertion = exertion;
  }

  public List<Cluster> getExertion() {
     return this.exertion ;
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

  public void setDevice(Cluster device) {
     this.device = device;
  }

  public Cluster getDevice() {
     return this.device ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
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
