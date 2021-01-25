package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AusgeschlosseneDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KategorieDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.UnbekannteDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Template("GECCO_Diagnose")
public class GECCODiagnoseComposition implements Composition {
  @Id
  private VersionUid versionUid;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/language")
  private Language language;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/context/other_context[at0001]/items[at0004]/value|defining_code")
  private StatusDefiningcode statusDefiningcode;

  @Path("/context/other_context[at0001]/items[at0005]/value|defining_code")
  private KategorieDefiningcode kategorieDefiningcode;

  @Path("/territory")
  private Territory territory;

  @Path("/content[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausgeschlossene Diagnose']")
  private AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/content[openEHR-EHR-EVALUATION.absence.v2 and name/value='Unbekannte Diagnose']")
  private UnbekannteDiagnoseEvaluation unbekannteDiagnose;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  @Path("/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Vorliegende Diagnose']")
  private VorliegendeDiagnoseEvaluation vorliegendeDiagnose;

  @Path("/context/location")
  private String location;

  @Path("/category|defining_code")
  private CategoryDefiningcode categoryDefiningcode;

  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setStatusDefiningcode(StatusDefiningcode statusDefiningcode) {
     this.statusDefiningcode = statusDefiningcode;
  }

  public StatusDefiningcode getStatusDefiningcode() {
     return this.statusDefiningcode ;
  }

  public void setKategorieDefiningcode(KategorieDefiningcode kategorieDefiningcode) {
     this.kategorieDefiningcode = kategorieDefiningcode;
  }

  public KategorieDefiningcode getKategorieDefiningcode() {
     return this.kategorieDefiningcode ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public void setAusgeschlosseneDiagnose(
      AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose) {
     this.ausgeschlosseneDiagnose = ausgeschlosseneDiagnose;
  }

  public AusgeschlosseneDiagnoseEvaluation getAusgeschlosseneDiagnose() {
     return this.ausgeschlosseneDiagnose ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setUnbekannteDiagnose(UnbekannteDiagnoseEvaluation unbekannteDiagnose) {
     this.unbekannteDiagnose = unbekannteDiagnose;
  }

  public UnbekannteDiagnoseEvaluation getUnbekannteDiagnose() {
     return this.unbekannteDiagnose ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
     this.settingDefiningcode = settingDefiningcode;
  }

  public SettingDefiningcode getSettingDefiningcode() {
     return this.settingDefiningcode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setVorliegendeDiagnose(VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
     this.vorliegendeDiagnose = vorliegendeDiagnose;
  }

  public VorliegendeDiagnoseEvaluation getVorliegendeDiagnose() {
     return this.vorliegendeDiagnose ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
     this.categoryDefiningcode = categoryDefiningcode;
  }

  public CategoryDefiningcode getCategoryDefiningcode() {
     return this.categoryDefiningcode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }
}
