package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pulse.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T16:40:29.051215811+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class PulseHeartBeatObservation implements EntryEntity {
  /**
   * Path: Self monitoring/Pulse/Heart beat/Any event/Rate
   * Description: The rate of the pulse or heart beat, measured in beats per minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double rateMagnitude;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Any event/Rate
   * Description: The rate of the pulse or heart beat, measured in beats per minute.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String rateUnits;

  /**
   * Path: Self monitoring/Pulse/Heart beat/history/Any event/structure/Rate/null_flavour
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour rateNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Any event/Level of exertion
   * Description: Record information about level of exertion.
   */
  @Path("/data[at0002]/events[at0003]/state[at0012]/items[openEHR-EHR-CLUSTER.level_of_exertion.v0]")
  private List<LevelOfExertionCluster2> levelOfExertion;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Any event/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Self monitoring/Pulse/Heart beat/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Method
   * Description: Method used to observe the pulse or heart beat.
   * Comment: For example, auscultation or electronic monitoring.
   */
  @Path("/protocol[at0010]/items[at1019]/value|defining_code")
  private MethodDefiningCode methodDefiningCode;

  /**
   * Path: Self monitoring/Pulse/Heart beat/List/Method/null_flavour
   */
  @Path("/protocol[at0010]/items[at1019]/null_flavour|defining_code")
  private NullFlavour methodNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Pulse/Heart beat/List/Body site/null_flavour
   */
  @Path("/protocol[at0010]/items[at1037]/null_flavour|defining_code")
  private NullFlavour bodySiteNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Device
   * Description: Details about the device used to measure the pulse rate or heart rate.
   */
  @Path("/protocol[at0010]/items[at1013]")
  private Cluster device;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Extension
   * Description: Additional information required to capture local content or to align with other reference models/formalisms.
   * Comment: For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> extension;

  /**
   * Path: Self monitoring/Pulse/Heart beat/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Self monitoring/Pulse/Heart beat/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Self monitoring/Pulse/Heart beat/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Body site
   * Description: Body site where the pulse or heart beat were observed.
   */
  @Path("/protocol[at0010]/items[at1037]/value")
  @Choice
  private PulseHeartBeatBodySiteChoice bodySite;

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

  public void setLevelOfExertion(List<LevelOfExertionCluster2> levelOfExertion) {
     this.levelOfExertion = levelOfExertion;
  }

  public List<LevelOfExertionCluster2> getLevelOfExertion() {
     return this.levelOfExertion ;
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

  public void setMethodDefiningCode(MethodDefiningCode methodDefiningCode) {
     this.methodDefiningCode = methodDefiningCode;
  }

  public MethodDefiningCode getMethodDefiningCode() {
     return this.methodDefiningCode ;
  }

  public void setMethodNullFlavourDefiningCode(NullFlavour methodNullFlavourDefiningCode) {
     this.methodNullFlavourDefiningCode = methodNullFlavourDefiningCode;
  }

  public NullFlavour getMethodNullFlavourDefiningCode() {
     return this.methodNullFlavourDefiningCode ;
  }

  public void setBodySiteNullFlavourDefiningCode(NullFlavour bodySiteNullFlavourDefiningCode) {
     this.bodySiteNullFlavourDefiningCode = bodySiteNullFlavourDefiningCode;
  }

  public NullFlavour getBodySiteNullFlavourDefiningCode() {
     return this.bodySiteNullFlavourDefiningCode ;
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

  public void setBodySite(PulseHeartBeatBodySiteChoice bodySite) {
     this.bodySite = bodySite;
  }

  public PulseHeartBeatBodySiteChoice getBodySite() {
     return this.bodySite ;
  }
}
