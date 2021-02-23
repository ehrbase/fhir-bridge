package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
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

public class D4LQuestionnaireCompositionContainment extends Containment {
  public SelectAqlField<D4LQuestionnaireComposition> D4_L_QUESTIONNAIRE_COMPOSITION = new AqlFieldImp<D4LQuestionnaireComposition>(D4LQuestionnaireComposition.class, "", "D4LQuestionnaireComposition", D4LQuestionnaireComposition.class, this);

  public SelectAqlField<ItemTree> TREE = new AqlFieldImp<ItemTree>(D4LQuestionnaireComposition.class, "/context/other_context[at0001]", "tree", ItemTree.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(D4LQuestionnaireComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(D4LQuestionnaireComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(D4LQuestionnaireComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(D4LQuestionnaireComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(D4LQuestionnaireComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(D4LQuestionnaireComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<AlterObservation> ALTER = new AqlFieldImp<AlterObservation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.age.v0]", "alter", AlterObservation.class, this);

  public SelectAqlField<WohnsituationEvaluation> WOHNSITUATION = new AqlFieldImp<WohnsituationEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.living_arrangement.v0]", "wohnsituation", WohnsituationEvaluation.class, this);

  public SelectAqlField<AusschlussPflegetaetigkeitEvaluation> AUSSCHLUSS_PFLEGETAETIGKEIT = new AqlFieldImp<AusschlussPflegetaetigkeitEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.exclusion_specific.v1]", "ausschlussPflegetaetigkeit", AusschlussPflegetaetigkeitEvaluation.class, this);

  public SelectAqlField<PflegetaetigkeitEvaluation> PFLEGETAETIGKEIT = new AqlFieldImp<PflegetaetigkeitEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.care_activity.v0]", "pflegetaetigkeit", PflegetaetigkeitEvaluation.class, this);

  public SelectAqlField<ZusammenfassungDerBeschaeftigungEvaluation> ZUSAMMENFASSUNG_DER_BESCHAEFTIGUNG = new AqlFieldImp<ZusammenfassungDerBeschaeftigungEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]", "zusammenfassungDerBeschaeftigung", ZusammenfassungDerBeschaeftigungEvaluation.class, this);

  public SelectAqlField<ZusammenfassungRauchverhaltenEvaluation> ZUSAMMENFASSUNG_RAUCHVERHALTEN = new AqlFieldImp<ZusammenfassungRauchverhaltenEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1]", "zusammenfassungRauchverhalten", ZusammenfassungRauchverhaltenEvaluation.class, this);

  public SelectAqlField<SchwangerschaftsstatusObservation> SCHWANGERSCHAFTSSTATUS = new AqlFieldImp<SchwangerschaftsstatusObservation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.pregnancy_status.v0]", "schwangerschaftsstatus", SchwangerschaftsstatusObservation.class, this);

  public SelectAqlField<KontaktAction> KONTAKT = new AqlFieldImp<KontaktAction>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-ACTION.contact.v0]", "kontakt", KontaktAction.class, this);

  public ListSelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE = new ListAqlFieldImp<ProblemDiagnoseEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "problemDiagnose", ProblemDiagnoseEvaluation.class, this);

  public SelectAqlField<ChronischeLungenkrankheitEvaluation> CHRONISCHE_LUNGENKRANKHEIT = new AqlFieldImp<ChronischeLungenkrankheitEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "chronischeLungenkrankheit", ChronischeLungenkrankheitEvaluation.class, this);

  public SelectAqlField<DiabetesEvaluation> DIABETES = new AqlFieldImp<DiabetesEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "diabetes", DiabetesEvaluation.class, this);

  public SelectAqlField<HerzerkrankungEvaluation> HERZERKRANKUNG = new AqlFieldImp<HerzerkrankungEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "herzerkrankung", HerzerkrankungEvaluation.class, this);

  public SelectAqlField<AdipositasEvaluation> ADIPOSITAS = new AqlFieldImp<AdipositasEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "adipositas", AdipositasEvaluation.class, this);

  public SelectAqlField<KortisionEvaluation> KORTISION = new AqlFieldImp<KortisionEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.medication_summary.v0]", "kortision", KortisionEvaluation.class, this);

  public SelectAqlField<ImmunsuppressivaEvaluation> IMMUNSUPPRESSIVA = new AqlFieldImp<ImmunsuppressivaEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.medication_summary.v0]", "immunsuppressiva", ImmunsuppressivaEvaluation.class, this);

  public SelectAqlField<ZusammenfassungDesImmunstatusEvaluation> ZUSAMMENFASSUNG_DES_IMMUNSTATUS = new AqlFieldImp<ZusammenfassungDesImmunstatusEvaluation>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.immunisation_summary.v0]", "zusammenfassungDesImmunstatus", ZusammenfassungDesImmunstatusEvaluation.class, this);

  public SelectAqlField<EinwilligungserklaerungAction> EINWILLIGUNGSERKLAERUNG = new AqlFieldImp<EinwilligungserklaerungAction>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-ACTION.informed_consent.v0]", "einwilligungserklaerung", EinwilligungserklaerungAction.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(D4LQuestionnaireComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(D4LQuestionnaireComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(D4LQuestionnaireComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(D4LQuestionnaireComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(D4LQuestionnaireComposition.class, "/territory", "territory", Territory.class, this);

  private D4LQuestionnaireCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.self_monitoring.v0");
  }

  public static D4LQuestionnaireCompositionContainment getInstance() {
    return new D4LQuestionnaireCompositionContainment();
  }
}
