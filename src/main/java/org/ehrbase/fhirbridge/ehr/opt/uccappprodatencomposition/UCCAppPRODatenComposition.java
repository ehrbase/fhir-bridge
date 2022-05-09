package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BloodPressureObservation;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BodyWeightObservation;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.PulseHeartBeatObservation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.self_monitoring.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T16:40:28.978471340+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@Template("UCC_App_PRO_Daten")
public class UCCAppPRODatenComposition implements CompositionEntity {
  /**
   * Path: Self monitoring/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Self monitoring/context/Extension
   * Description: Additional information required to capture local context or to align with other reference models/formalisms.
   * Comment: For example: Local hospital departmental infomation or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> extension;

  /**
   * Path: Self monitoring/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Self monitoring/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Self monitoring/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Self monitoring/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Self monitoring/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Self monitoring/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Self monitoring/Blood pressure
   * Description: The local measurement of arterial blood pressure which is a surrogate for arterial pressure in the systemic circulation.
   * Comment: Most commonly, use of the term 'blood pressure' refers to measurement of brachial artery pressure in the upper arm.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.blood_pressure.v2]")
  private BloodPressureObservation bloodPressure;

  /**
   * Path: Self monitoring/Body weight
   * Description: Measurement of the body weight of an individual.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.body_weight.v2]")
  private BodyWeightObservation bodyWeight;

  /**
   * Path: Self monitoring/Pulse/Heart beat
   * Description: The rate and associated attributes for a pulse or heart beat.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.pulse.v2]")
  private PulseHeartBeatObservation pulseHeartBeat;

  /**
   * Path: Self monitoring/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Self monitoring/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Self monitoring/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Self monitoring/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
     this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
     return this.settingDefiningCode ;
  }

  public void setBloodPressure(BloodPressureObservation bloodPressure) {
     this.bloodPressure = bloodPressure;
  }

  public BloodPressureObservation getBloodPressure() {
     return this.bloodPressure ;
  }

  public void setBodyWeight(BodyWeightObservation bodyWeight) {
     this.bodyWeight = bodyWeight;
  }

  public BodyWeightObservation getBodyWeight() {
     return this.bodyWeight ;
  }

  public void setPulseHeartBeat(PulseHeartBeatObservation pulseHeartBeat) {
     this.pulseHeartBeat = pulseHeartBeat;
  }

  public PulseHeartBeatObservation getPulseHeartBeat() {
     return this.pulseHeartBeat ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
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

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
