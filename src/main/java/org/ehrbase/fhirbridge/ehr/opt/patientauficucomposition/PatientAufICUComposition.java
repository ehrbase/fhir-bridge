package org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition;

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
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.StatusDefiningCode;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:55:47.571474+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Patient auf ICU")
public class PatientAufICUComposition implements CompositionEntity {
  /**
   * Path: Patient auf der Intensivstation/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Patient auf der Intensivstation/context/Erweiterung
   * Description: Ergänzende Angaben zum Registereintrag. 
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /**
   * Path: Patient auf der Intensivstation/context/Status
   * Description: Status der gelieferten Daten für den Registereintrag. Hinweis: Dies ist nicht der Status einzelner Komponenten.
   */
  @Path("/context/other_context[at0001]/items[at0004]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * Path: Patient auf der Intensivstation/context/Baum/Status/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Patient auf der Intensivstation/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Patient auf der Intensivstation/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Patient auf der Intensivstation/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Patient auf der Intensivstation/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Patient auf der Intensivstation/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Patient auf der Intensivstation/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation
   * Description: Ein Personen- oder Selbstbeurteilungs-Screening-Fragebogen zu erfolgten Behandlung oder Verwaltungsmaßnahmen.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.management_screening.v0 and name/value='Patient auf der Intensivstation']")
  private PatientAufDerIntensivstationObservation patientAufDerIntensivstation;

  /**
   * Path: Patient auf der Intensivstation/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Patient auf der Intensivstation/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Patient auf der Intensivstation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Patient auf der Intensivstation/territory
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

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setStatusDefiningCode(StatusDefiningCode statusDefiningCode) {
     this.statusDefiningCode = statusDefiningCode;
  }

  public StatusDefiningCode getStatusDefiningCode() {
     return this.statusDefiningCode ;
  }

  public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
     this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
  }

  public NullFlavour getStatusNullFlavourDefiningCode() {
     return this.statusNullFlavourDefiningCode ;
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

  public void setPatientAufDerIntensivstation(
      PatientAufDerIntensivstationObservation patientAufDerIntensivstation) {
     this.patientAufDerIntensivstation = patientAufDerIntensivstation;
  }

  public PatientAufDerIntensivstationObservation getPatientAufDerIntensivstation() {
     return this.patientAufDerIntensivstation ;
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
