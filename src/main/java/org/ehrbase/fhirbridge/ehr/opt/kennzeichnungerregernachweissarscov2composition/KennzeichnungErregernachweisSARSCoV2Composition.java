package org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:20.601029+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Kennzeichnung Erregernachweis SARS-CoV-2")
public class KennzeichnungErregernachweisSARSCoV2Composition implements CompositionEntity, Composition {
  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/Bericht Name
   * Description: Identifizierungsmerkmal des Berichts.
   */
  @Path("/context/other_context[at0001]/items[at0002 and name/value='Bericht Name']/value|value")
  private String berichtNameValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/Tree/Bericht Name/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0002 and name/value='Bericht Name']/null_flavour|defining_code")
  private NullFlavour berichtNameNullFlavourDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/Fallidentifikation
   * Description: Zur Erfassung von Details zur Identifikation eines Falls im Gesundheitswesen.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
  private FallidentifikationCluster fallidentifikation;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis
   * Description: Dokumentation eines Nachweis eines Erregers bei einem Patienten.
   */
  @Path("/content[openEHR-EHR-EVALUATION.flag_pathogen.v0]")
  private KennzeichnungErregernachweisEvaluation kennzeichnungErregernachweis;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/territory
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

  public void setBerichtNameValue(String berichtNameValue) {
     this.berichtNameValue = berichtNameValue;
  }

  public String getBerichtNameValue() {
     return this.berichtNameValue ;
  }

  public void setBerichtNameNullFlavourDefiningCode(
      NullFlavour berichtNameNullFlavourDefiningCode) {
     this.berichtNameNullFlavourDefiningCode = berichtNameNullFlavourDefiningCode;
  }

  public NullFlavour getBerichtNameNullFlavourDefiningCode() {
     return this.berichtNameNullFlavourDefiningCode ;
  }

  public void setFallidentifikation(FallidentifikationCluster fallidentifikation) {
     this.fallidentifikation = fallidentifikation;
  }

  public FallidentifikationCluster getFallidentifikation() {
     return this.fallidentifikation ;
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

  public void setKennzeichnungErregernachweis(
      KennzeichnungErregernachweisEvaluation kennzeichnungErregernachweis) {
     this.kennzeichnungErregernachweis = kennzeichnungErregernachweis;
  }

  public KennzeichnungErregernachweisEvaluation getKennzeichnungErregernachweis() {
     return this.kennzeichnungErregernachweis ;
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
