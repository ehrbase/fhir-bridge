package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
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
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.GeschlechtEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.VitalstatusEvaluation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.person.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.095154239+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@Template("KDS_Person")
public class KDSPersonComposition implements CompositionEntity {
  /**
   * Path: Person/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Person/context/Patient ID
   * Description: ID der Person
   */
  @Path("/context/other_context[at0003]/items[at0004 and name/value='Patient ID']/value")
  private DvIdentifier patientId;

  /**
   * Path: Person/context/Item tree/Patient ID/null_flavour
   */
  @Path("/context/other_context[at0003]/items[at0004 and name/value='Patient ID']/null_flavour|defining_code")
  private NullFlavour patientIdNullFlavourDefiningCode;

  /**
   * Path: Person/context/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/context/other_context[at0003]/items[at0005]")
  private List<Cluster> erweiterung;

  /**
   * Path: Person/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Person/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Person/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Person/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Person/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Person/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Person/Geschlecht
   * Description: Detaillierte Beschreibung des Geschlechts einer Person.
   */
  @Path("/content[openEHR-EHR-EVALUATION.gender.v1]")
  private GeschlechtEvaluation geschlecht;

  /**
   * Path: Person/Personendaten
   * Description: Demografische Daten zu einer Person wie Geburtsdatum und Telefonnummer.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.person_data.v0]")
  private PersonendatenAdminEntry personendaten;

  /**
   * Path: Person/Vitalstatus
   * Description: Zur Darstellung des Vitalstatus ("Letzter bekannter Lebenszeitpunkt") eines Patienten, der mindestens bei der Aufnahme oder Entlassung festgestellt wird.
   */
  @Path("/content[openEHR-EHR-EVALUATION.vital_status.v1]")
  private VitalstatusEvaluation vitalstatus;

  /**
   * Path: Person/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Person/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Person/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Person/territory
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

  public void setPatientId(DvIdentifier patientId) {
     this.patientId = patientId;
  }

  public DvIdentifier getPatientId() {
     return this.patientId ;
  }

  public void setPatientIdNullFlavourDefiningCode(NullFlavour patientIdNullFlavourDefiningCode) {
     this.patientIdNullFlavourDefiningCode = patientIdNullFlavourDefiningCode;
  }

  public NullFlavour getPatientIdNullFlavourDefiningCode() {
     return this.patientIdNullFlavourDefiningCode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
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

  public void setGeschlecht(GeschlechtEvaluation geschlecht) {
     this.geschlecht = geschlecht;
  }

  public GeschlechtEvaluation getGeschlecht() {
     return this.geschlecht ;
  }

  public void setPersonendaten(PersonendatenAdminEntry personendaten) {
     this.personendaten = personendaten;
  }

  public PersonendatenAdminEntry getPersonendaten() {
     return this.personendaten ;
  }

  public void setVitalstatus(VitalstatusEvaluation vitalstatus) {
     this.vitalstatus = vitalstatus;
  }

  public VitalstatusEvaluation getVitalstatus() {
     return this.vitalstatus ;
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
