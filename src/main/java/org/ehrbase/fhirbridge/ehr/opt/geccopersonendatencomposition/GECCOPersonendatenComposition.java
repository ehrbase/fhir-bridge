package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeccoPersonendatenKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.StatusDefiningCode;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-15T13:49:29.975023+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@Template("GECCO_Personendaten")
public class GECCOPersonendatenComposition implements CompositionEntity {
  /**
   * Path: GECCO_Personendaten/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: GECCO_Personendaten/context/Erweiterung
   * Description: Ergänzende Angaben zum Registereintrag.
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Personendaten/context/Status
   * Description: Status der gelieferten Daten für den Registereintrag. Hinweis: Dies ist nicht der Status einzelner Komponenten.
   */
  @Path("/context/other_context[at0001]/items[at0004]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * Path: GECCO_Personendaten/context/Baum/Status/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/context/Kategorie
   * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
   */
  @Path("/context/other_context[at0001]/items[at0005]")
  private List<GeccoPersonendatenKategorieElement> kategorie;

  /**
   * Path: GECCO_Personendaten/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: GECCO_Personendaten/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: GECCO_Personendaten/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: GECCO_Personendaten/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: GECCO_Personendaten/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: GECCO_Personendaten/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten
   * Description: Demografische Daten zu einer Person wie Geburtsdatum und Telefonnummer.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.person_data.v0]")
  private PersonendatenAdminEntry personendaten;

  /**
   * Path: GECCO_Personendaten/Alter
   * Description: Angaben über das Alter einer Person zu einem bestimmten Zeitpunkt.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.age.v0]")
  private AlterObservation alter;

  /**
   * Path: GECCO_Personendaten/Geschlecht
   * Description: Detaillierte Beschreibung des Geschlechts einer Person.
   */
  @Path("/content[openEHR-EHR-EVALUATION.gender.v1]")
  private GeschlechtEvaluation geschlecht;

  /**
   * Path: GECCO_Personendaten/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: GECCO_Personendaten/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Personendaten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Personendaten/territory
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

  public void setKategorie(List<GeccoPersonendatenKategorieElement> kategorie) {
     this.kategorie = kategorie;
  }

  public List<GeccoPersonendatenKategorieElement> getKategorie() {
     return this.kategorie ;
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

  public void setPersonendaten(PersonendatenAdminEntry personendaten) {
     this.personendaten = personendaten;
  }

  public PersonendatenAdminEntry getPersonendaten() {
     return this.personendaten ;
  }

  public void setAlter(AlterObservation alter) {
     this.alter = alter;
  }

  public AlterObservation getAlter() {
     return this.alter ;
  }

  public void setGeschlecht(GeschlechtEvaluation geschlecht) {
     this.geschlecht = geschlecht;
  }

  public GeschlechtEvaluation getGeschlecht() {
     return this.geschlecht ;
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
