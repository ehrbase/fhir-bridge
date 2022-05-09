package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
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
@Archetype("openEHR-EHR-OBSERVATION.body_weight.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T16:40:29.047988795+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class BodyWeightObservation implements EntryEntity {
  /**
   * Path: Self monitoring/Body weight/Any event/Weight
   * Description: The weight of the individual.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double weightMagnitude;

  /**
   * Path: Self monitoring/Body weight/Any event/Weight
   * Description: The weight of the individual.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String weightUnits;

  /**
   * Path: Self monitoring/Body weight/history/Any event/Simple/Weight/null_flavour
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour weightNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Body weight/Any event/state structure
   * Description: @ internal @
   */
  @Path("/data[at0002]/events[at0003]/state[at0008]")
  private ItemTree stateStructure;

  /**
   * Path: Self monitoring/Body weight/Any event/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Self monitoring/Body weight/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Self monitoring/Body weight/Device
   * Description: Details about the weighing device.
   */
  @Path("/protocol[at0015]/items[at0020]")
  private Cluster device;

  /**
   * Path: Self monitoring/Body weight/Extension
   * Description: Additional information required to capture local content or to align with other reference models/formalisms.
   * Comment: For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0015]/items[at0027]")
  private List<Cluster> extension;

  /**
   * Path: Self monitoring/Body weight/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Self monitoring/Body weight/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Self monitoring/Body weight/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setWeightMagnitude(Double weightMagnitude) {
     this.weightMagnitude = weightMagnitude;
  }

  public Double getWeightMagnitude() {
     return this.weightMagnitude ;
  }

  public void setWeightUnits(String weightUnits) {
     this.weightUnits = weightUnits;
  }

  public String getWeightUnits() {
     return this.weightUnits ;
  }

  public void setWeightNullFlavourDefiningCode(NullFlavour weightNullFlavourDefiningCode) {
     this.weightNullFlavourDefiningCode = weightNullFlavourDefiningCode;
  }

  public NullFlavour getWeightNullFlavourDefiningCode() {
     return this.weightNullFlavourDefiningCode ;
  }

  public void setStateStructure(ItemTree stateStructure) {
     this.stateStructure = stateStructure;
  }

  public ItemTree getStateStructure() {
     return this.stateStructure ;
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
