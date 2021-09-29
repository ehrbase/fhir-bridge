package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.HipMetadataCluster;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.MediendateiCluster;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-24T22:02:30.769662800+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@Template("HIP Document")
public class HIPDocumentComposition implements CompositionEntity {
  /**
   * Path: Bericht/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Bericht/context/Bericht ID
   * Description: Identifizierungsmerkmal des Berichts.
   */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Path: Bericht/context/Tree/Bericht ID/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour berichtIdNullFlavourDefiningCode;

  /**
   * Path: Bericht/context/Status
   * Description: Der Status des gesamten Berichts. Hinweis: Dies ist nicht der Status einer Berichtskomponente.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Path: Bericht/context/Tree/Status/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Bericht/context/Hip metadata
   * Description: unknown
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.hip_metadata.v0]")
  private HipMetadataCluster hipMetadata;

  /**
   * Path: Bericht/context/Mediendatei
   * Description: Eine Mediendatei mit zugehörigen Metadaten, die im Rahmen des Gesundheitsprozesses erworben oder verwendet wird.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.media_file.v0]")
  private MediendateiCluster mediendatei;

  /**
   * Path: Bericht/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Bericht/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Bericht/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Bericht/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Bericht/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Bericht/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Bericht/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Bericht/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Bericht/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Bericht/territory
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

  public void setBerichtIdValue(String berichtIdValue) {
     this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
     return this.berichtIdValue ;
  }

  public void setBerichtIdNullFlavourDefiningCode(NullFlavour berichtIdNullFlavourDefiningCode) {
     this.berichtIdNullFlavourDefiningCode = berichtIdNullFlavourDefiningCode;
  }

  public NullFlavour getBerichtIdNullFlavourDefiningCode() {
     return this.berichtIdNullFlavourDefiningCode ;
  }

  public void setStatusValue(String statusValue) {
     this.statusValue = statusValue;
  }

  public String getStatusValue() {
     return this.statusValue ;
  }

  public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
     this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
  }

  public NullFlavour getStatusNullFlavourDefiningCode() {
     return this.statusNullFlavourDefiningCode ;
  }

  public void setHipMetadata(HipMetadataCluster hipMetadata) {
     this.hipMetadata = hipMetadata;
  }

  public HipMetadataCluster getHipMetadata() {
     return this.hipMetadata ;
  }

  public void setMediendatei(MediendateiCluster mediendatei) {
     this.mediendatei = mediendatei;
  }

  public MediendateiCluster getMediendatei() {
     return this.mediendatei ;
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
