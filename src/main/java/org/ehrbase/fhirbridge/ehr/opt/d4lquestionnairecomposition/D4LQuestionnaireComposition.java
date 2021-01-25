package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
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
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AdipositasEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AusschlussPflegetaetigkeitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ChronischeLungenkrankheitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.DiabetesEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.EinwilligungserklaerungAction;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.HerzerkrankungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ImmunsuppressivaEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KontaktAction;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KortisionEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.PflegetaetigkeitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ProblemDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.WohnsituationEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDerBeschaeftigungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDesImmunstatusEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungRauchverhaltenEvaluation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.self_monitoring.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.732275+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@Template("D4L_questionnaire")
public class D4LQuestionnaireComposition implements CompositionEntity, Composition {
  /**
   * Path: Selbstüberwachung/context/Tree
   * Description: @ internal @
   */
  @Path("/context/other_context[at0001]")
  private ItemTree tree;

  /**
   * Path: Selbstüberwachung/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Selbstüberwachung/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Selbstüberwachung/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Selbstüberwachung/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Selbstüberwachung/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Selbstüberwachung/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter
   * Description: Angaben über das Alter einer Person zu einem bestimmten Zeitpunkt.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.age.v0]")
  private AlterObservation alter;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation
   * Description: Die Umstände über eine Person, das allein oder mit anderen zusammen lebt.
   * Comment: Diese Information bietet einen Einblick in die tägliche Unterstützung, zu der eine Person in ihrer häuslichen Umgebung - sowohl körperlich als auch emotional - Zugang hat.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private WohnsituationEvaluation wohnsituation;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit
   * Description: Ein Bericht über den Ausschluss eines/r Problems/Diagnose, familiäre Krankengeschichte, Medikation, Nebenwirkung/Allergens oder eines anderen klinischen Ereignisses, welche/s zur Zeit nicht oder noch nie vorhanden war.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausschluss - Pflegetätigkeit']")
  private AusschlussPflegetaetigkeitEvaluation ausschlussPflegetaetigkeit;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit
   * Description: Zusammenfassende oder fortlaufende Informationen über die ausgeführte Pflege oder Unterstützung die eine Person einer oder mehrerer anderer Personen leistet.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.care_activity.v0]")
  private PflegetaetigkeitEvaluation pflegetaetigkeit;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung
   * Description: Zusammenfassung oder beständige Information zu aktuellen und früheren Jobs und / oder Rollen einer Person.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1]")
  private ZusammenfassungDerBeschaeftigungEvaluation zusammenfassungDerBeschaeftigung;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten
   * Description: Zusammenfassende oder persistente Informationen über die Tabakrauchgewohnheiten einer Person.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1]")
  private ZusammenfassungRauchverhaltenEvaluation zusammenfassungRauchverhalten;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus
   * Description: Angabe darüber, ob die Person schwanger ist oder schwanger sein könnte oder nicht.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.pregnancy_status.v0]")
  private SchwangerschaftsstatusObservation schwangerschaftsstatus;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt
   * Description: unknown
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-ACTION.contact.v0]")
  private KontaktAction kontakt;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<ProblemDiagnoseEvaluation> problemDiagnose;

  /**
   * Path: Selbstüberwachung/Vor-/Grunderkrankungen/Chronische Lungenkrankheit
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Vor-/Grunderkrankungen']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Chronische Lungenkrankheit']")
  private ChronischeLungenkrankheitEvaluation chronischeLungenkrankheit;

  /**
   * Path: Selbstüberwachung/Vor-/Grunderkrankungen/Diabetes
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Vor-/Grunderkrankungen']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Diabetes']")
  private DiabetesEvaluation diabetes;

  /**
   * Path: Selbstüberwachung/Vor-/Grunderkrankungen/Herzerkrankung
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Vor-/Grunderkrankungen']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Herzerkrankung']")
  private HerzerkrankungEvaluation herzerkrankung;

  /**
   * Path: Selbstüberwachung/Vor-/Grunderkrankungen/Adipositas
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Vor-/Grunderkrankungen']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Adipositas']")
  private AdipositasEvaluation adipositas;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision
   * Description: Zusammenfassende Informationen über die Verabreichungs- oder Verbrauchshistorie für ein bestimmtes Medikament oder eine bestimmte Medikamentenklasse über die Lebenszeit der Person.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Medikamente / Impfungen']/items[openEHR-EHR-EVALUATION.medication_summary.v0 and name/value='Kortision']")
  private KortisionEvaluation kortision;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Immunsuppressiva
   * Description: Zusammenfassende Informationen über die Verabreichungs- oder Verbrauchshistorie für ein bestimmtes Medikament oder eine bestimmte Medikamentenklasse über die Lebenszeit der Person.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Medikamente / Impfungen']/items[openEHR-EHR-EVALUATION.medication_summary.v0 and name/value='Immunsuppressiva']")
  private ImmunsuppressivaEvaluation immunsuppressiva;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus
   * Description: Zusammenfassung des Immunstatus für eine identifizierte Infektionskrankheit oder Erreger.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Medikamente / Impfungen']/items[openEHR-EHR-EVALUATION.immunisation_summary.v0]")
  private ZusammenfassungDesImmunstatusEvaluation zusammenfassungDesImmunstatus;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung
   * Description: Aufzeichnungen über den Status und Einzelheiten der Einwilligungserklärung eines Patienten (oder seines Vertreters) zur einen vorgeschlagenen Prozedur, Studie oder einen anderen gesundheitsbezogenen Aktivität (einschließlich Behandlungen und Untersuchungen) auf der Grundlage einer klaren Einschätzung und eines klaren Verständnisses der Fakten, Auswirkungen und möglichen zukünftigen Konsequenzen durch den Patienten oder dessen Vertreter.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Datenspende']/items[openEHR-EHR-ACTION.informed_consent.v0]")
  private EinwilligungserklaerungAction einwilligungserklaerung;

  /**
   * Path: Selbstüberwachung/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Selbstüberwachung/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Selbstüberwachung/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setTree(ItemTree tree) {
     this.tree = tree;
  }

  public ItemTree getTree() {
     return this.tree ;
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

  public void setAlter(AlterObservation alter) {
     this.alter = alter;
  }

  public AlterObservation getAlter() {
     return this.alter ;
  }

  public void setWohnsituation(WohnsituationEvaluation wohnsituation) {
     this.wohnsituation = wohnsituation;
  }

  public WohnsituationEvaluation getWohnsituation() {
     return this.wohnsituation ;
  }

  public void setAusschlussPflegetaetigkeit(
      AusschlussPflegetaetigkeitEvaluation ausschlussPflegetaetigkeit) {
     this.ausschlussPflegetaetigkeit = ausschlussPflegetaetigkeit;
  }

  public AusschlussPflegetaetigkeitEvaluation getAusschlussPflegetaetigkeit() {
     return this.ausschlussPflegetaetigkeit ;
  }

  public void setPflegetaetigkeit(PflegetaetigkeitEvaluation pflegetaetigkeit) {
     this.pflegetaetigkeit = pflegetaetigkeit;
  }

  public PflegetaetigkeitEvaluation getPflegetaetigkeit() {
     return this.pflegetaetigkeit ;
  }

  public void setZusammenfassungDerBeschaeftigung(
      ZusammenfassungDerBeschaeftigungEvaluation zusammenfassungDerBeschaeftigung) {
     this.zusammenfassungDerBeschaeftigung = zusammenfassungDerBeschaeftigung;
  }

  public ZusammenfassungDerBeschaeftigungEvaluation getZusammenfassungDerBeschaeftigung() {
     return this.zusammenfassungDerBeschaeftigung ;
  }

  public void setZusammenfassungRauchverhalten(
      ZusammenfassungRauchverhaltenEvaluation zusammenfassungRauchverhalten) {
     this.zusammenfassungRauchverhalten = zusammenfassungRauchverhalten;
  }

  public ZusammenfassungRauchverhaltenEvaluation getZusammenfassungRauchverhalten() {
     return this.zusammenfassungRauchverhalten ;
  }

  public void setSchwangerschaftsstatus(SchwangerschaftsstatusObservation schwangerschaftsstatus) {
     this.schwangerschaftsstatus = schwangerschaftsstatus;
  }

  public SchwangerschaftsstatusObservation getSchwangerschaftsstatus() {
     return this.schwangerschaftsstatus ;
  }

  public void setKontakt(KontaktAction kontakt) {
     this.kontakt = kontakt;
  }

  public KontaktAction getKontakt() {
     return this.kontakt ;
  }

  public void setProblemDiagnose(List<ProblemDiagnoseEvaluation> problemDiagnose) {
     this.problemDiagnose = problemDiagnose;
  }

  public List<ProblemDiagnoseEvaluation> getProblemDiagnose() {
     return this.problemDiagnose ;
  }

  public void setChronischeLungenkrankheit(
      ChronischeLungenkrankheitEvaluation chronischeLungenkrankheit) {
     this.chronischeLungenkrankheit = chronischeLungenkrankheit;
  }

  public ChronischeLungenkrankheitEvaluation getChronischeLungenkrankheit() {
     return this.chronischeLungenkrankheit ;
  }

  public void setDiabetes(DiabetesEvaluation diabetes) {
     this.diabetes = diabetes;
  }

  public DiabetesEvaluation getDiabetes() {
     return this.diabetes ;
  }

  public void setHerzerkrankung(HerzerkrankungEvaluation herzerkrankung) {
     this.herzerkrankung = herzerkrankung;
  }

  public HerzerkrankungEvaluation getHerzerkrankung() {
     return this.herzerkrankung ;
  }

  public void setAdipositas(AdipositasEvaluation adipositas) {
     this.adipositas = adipositas;
  }

  public AdipositasEvaluation getAdipositas() {
     return this.adipositas ;
  }

  public void setKortision(KortisionEvaluation kortision) {
     this.kortision = kortision;
  }

  public KortisionEvaluation getKortision() {
     return this.kortision ;
  }

  public void setImmunsuppressiva(ImmunsuppressivaEvaluation immunsuppressiva) {
     this.immunsuppressiva = immunsuppressiva;
  }

  public ImmunsuppressivaEvaluation getImmunsuppressiva() {
     return this.immunsuppressiva ;
  }

  public void setZusammenfassungDesImmunstatus(
      ZusammenfassungDesImmunstatusEvaluation zusammenfassungDesImmunstatus) {
     this.zusammenfassungDesImmunstatus = zusammenfassungDesImmunstatus;
  }

  public ZusammenfassungDesImmunstatusEvaluation getZusammenfassungDesImmunstatus() {
     return this.zusammenfassungDesImmunstatus ;
  }

  public void setEinwilligungserklaerung(EinwilligungserklaerungAction einwilligungserklaerung) {
     this.einwilligungserklaerung = einwilligungserklaerung;
  }

  public EinwilligungserklaerungAction getEinwilligungserklaerung() {
     return this.einwilligungserklaerung ;
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

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
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
