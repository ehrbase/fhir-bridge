package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.PrimaercodeEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.ProblemDiagnoseAttributCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.SekundaercodeEvaluation;

public class KDSDiagnoseCompositionContainment extends Containment {
  public SelectAqlField<KDSDiagnoseComposition> K_D_S_DIAGNOSE_COMPOSITION = new AqlFieldImp<KDSDiagnoseComposition>(KDSDiagnoseComposition.class, "", "KDSDiagnoseComposition", KDSDiagnoseComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(KDSDiagnoseComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(KDSDiagnoseComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<NullFlavour> BERICHT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KDSDiagnoseComposition.class, "/context/other_context[at0001]/items[at0002]/null_flavour|defining_code", "berichtIdNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(KDSDiagnoseComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KDSDiagnoseComposition.class, "/context/other_context[at0001]/items[at0005]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(KDSDiagnoseComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<ProblemDiagnoseAttributCluster> PROBLEM_DIAGNOSE_ATTRIBUT = new AqlFieldImp<ProblemDiagnoseAttributCluster>(KDSDiagnoseComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v2]", "problemDiagnoseAttribut", ProblemDiagnoseAttributCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KDSDiagnoseComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(KDSDiagnoseComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KDSDiagnoseComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(KDSDiagnoseComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(KDSDiagnoseComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(KDSDiagnoseComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public ListSelectAqlField<PrimaercodeEvaluation> PRIMAERCODE = new ListAqlFieldImp<PrimaercodeEvaluation>(KDSDiagnoseComposition.class, "/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "primaercode", PrimaercodeEvaluation.class, this);

  public ListSelectAqlField<SekundaercodeEvaluation> SEKUNDAERCODE = new ListAqlFieldImp<SekundaercodeEvaluation>(KDSDiagnoseComposition.class, "/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "sekundaercode", SekundaercodeEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(KDSDiagnoseComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KDSDiagnoseComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KDSDiagnoseComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(KDSDiagnoseComposition.class, "/territory", "territory", Territory.class, this);

  private KDSDiagnoseCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static KDSDiagnoseCompositionContainment getInstance() {
    return new KDSDiagnoseCompositionContainment();
  }
}
