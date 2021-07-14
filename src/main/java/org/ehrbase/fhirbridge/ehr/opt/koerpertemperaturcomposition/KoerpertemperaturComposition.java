package org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.definition.GeccoKoerpertemperaturKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.definition.KoerpertemperaturObservation;
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.definition.StatusDefiningCode;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-06T17:04:45.306134+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
@Template("Koerpertemperatur")
public class KoerpertemperaturComposition implements CompositionEntity {
  /**
   * Path: GECCO_Koerpertemperatur/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: GECCO_Koerpertemperatur/context/Erweiterung
   * Description: Ergänzende Angaben zum Registereintrag.
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Koerpertemperatur/context/Status
   * Description: Status der gelieferten Daten für den Registereintrag. Hinweis: Dies ist nicht der Status einzelner Komponenten.
   */
  @Path("/context/other_context[at0001]/items[at0004]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * Path: GECCO_Koerpertemperatur/context/Baum/Status/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: GECCO_Koerpertemperatur/context/Kategorie
   * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
   */
  @Path("/context/other_context[at0001]/items[at0005]")
  private List<GeccoKoerpertemperaturKategorieElement> kategorie;

  /**
   * Path: GECCO_Koerpertemperatur/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: GECCO_Koerpertemperatur/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: GECCO_Koerpertemperatur/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: GECCO_Koerpertemperatur/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: GECCO_Koerpertemperatur/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: GECCO_Koerpertemperatur/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: GECCO_Koerpertemperatur/Koerpertemperatur
   * Description: Eine Messung der Körpertemperatur, als Surrogat für die Temperatur des gesamten Körper der Person.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.body_temperature.v2 and name/value='Koerpertemperatur']")
  private KoerpertemperaturObservation koerpertemperatur;

  /**
   * Path: GECCO_Koerpertemperatur/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: GECCO_Koerpertemperatur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Koerpertemperatur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Koerpertemperatur/territory
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

  public void setKategorie(List<GeccoKoerpertemperaturKategorieElement> kategorie) {
     this.kategorie = kategorie;
  }

  public List<GeccoKoerpertemperaturKategorieElement> getKategorie() {
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

  public void setKoerpertemperatur(KoerpertemperaturObservation koerpertemperatur) {
     this.koerpertemperatur = koerpertemperatur;
  }

  public KoerpertemperaturObservation getKoerpertemperatur() {
     return this.koerpertemperatur ;
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
