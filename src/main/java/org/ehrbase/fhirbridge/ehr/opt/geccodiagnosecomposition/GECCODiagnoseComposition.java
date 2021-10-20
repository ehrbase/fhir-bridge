package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AusgeschlosseneDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.UnbekannteDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-19T15:40:13.451043+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@Template("GECCO_Diagnose")
public class GECCODiagnoseComposition implements CompositionEntity {
  /**
   * Path: GECCO_Diagnose/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: GECCO_Diagnose/context/Erweiterung
   * Description: Ergänzende Angaben zum Registereintrag.
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Diagnose/context/Status
   * Description: Status der gelieferten Daten für den Registereintrag. Hinweis: Dies ist nicht der Status einzelner Komponenten.
   */
  @Path("/context/other_context[at0001]/items[at0004]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * Path: GECCO_Diagnose/context/Baum/Status/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: GECCO_Diagnose/context/Kategorie
   * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
   */
  @Path("/context/other_context[at0001]/items[at0005]/value")
  private DvCodedText kategorie;

  /**
   * Path: GECCO_Diagnose/context/Baum/Kategorie/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour kategorieNullFlavourDefiningCode;

  /**
   * Path: GECCO_Diagnose/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: GECCO_Diagnose/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: GECCO_Diagnose/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: GECCO_Diagnose/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: GECCO_Diagnose/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: GECCO_Diagnose/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Vorliegende Diagnose']")
  private VorliegendeDiagnoseEvaluation vorliegendeDiagnose;

  /**
   * Path: GECCO_Diagnose/Ausgeschlossene Diagnose
   * Description: Ein Bericht über den Ausschluss eines/r Problems/Diagnose, familiäre Krankengeschichte, Medikation, Nebenwirkung/Allergens oder eines anderen klinischen Ereignisses, welche/s zur Zeit nicht oder noch nie vorhanden war.
   */
  @Path("/content[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausgeschlossene Diagnose']")
  private AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose;

  /**
   * Path: GECCO_Diagnose/Unbekannte Diagnose
   * Description: Aussage darüber, dass bestimmte Gesundheitsinformationen zum Zeitpukt der Erfassung nicht in der Krankenakte oder einem Schriftstück erfasst werden können, da keine Kenntnisse darüber vorhanden sind.
   */
  @Path("/content[openEHR-EHR-EVALUATION.absence.v2 and name/value='Unbekannte Diagnose']")
  private UnbekannteDiagnoseEvaluation unbekannteDiagnose;

  /**
   * Path: GECCO_Diagnose/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: GECCO_Diagnose/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Diagnose/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Diagnose/territory
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

  public void setKategorie(DvCodedText kategorie) {
     this.kategorie = kategorie;
  }

  public DvCodedText getKategorie() {
     return this.kategorie ;
  }

  public void setKategorieNullFlavourDefiningCode(NullFlavour kategorieNullFlavourDefiningCode) {
     this.kategorieNullFlavourDefiningCode = kategorieNullFlavourDefiningCode;
  }

  public NullFlavour getKategorieNullFlavourDefiningCode() {
     return this.kategorieNullFlavourDefiningCode ;
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

  public void setVorliegendeDiagnose(VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
     this.vorliegendeDiagnose = vorliegendeDiagnose;
  }

  public VorliegendeDiagnoseEvaluation getVorliegendeDiagnose() {
     return this.vorliegendeDiagnose ;
  }

  public void setAusgeschlosseneDiagnose(
      AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose) {
     this.ausgeschlosseneDiagnose = ausgeschlosseneDiagnose;
  }

  public AusgeschlosseneDiagnoseEvaluation getAusgeschlosseneDiagnose() {
     return this.ausgeschlosseneDiagnose ;
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
