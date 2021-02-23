package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProblemDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE_EVALUATION = new AqlFieldImp<ProblemDiagnoseEvaluation>(ProblemDiagnoseEvaluation.class, "", "ProblemDiagnoseEvaluation", ProblemDiagnoseEvaluation.class, this);

  public SelectAqlField<String> NAME_DES_PROBLEMS_DER_DIAGNOSE_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|value", "nameDesProblemsDerDiagnoseValue", String.class, this);

  public SelectAqlField<NullFlavour> NAME_DES_PROBLEMS_DER_DIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "nameDesProblemsDerDiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0039]", "anatomischeStelleStrukturiert", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_ZEITPUNKT_DES_AUFTRETENS_DER_ERSTDIAGNOSE_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0077]/value|value", "datumZeitpunktDesAuftretensDerErstdiagnoseValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_ZEITPUNKT_DES_AUFTRETENS_DER_ERSTDIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0077]/null_flavour|defining_code", "datumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FieberInDenLetzten24StundenCluster> FIEBER_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<FieberInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "fieberInDenLetzten24Stunden", FieberInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<FieberInDenLetzten4TagenCluster> FIEBER_IN_DEN_LETZTEN4_TAGEN = new AqlFieldImp<FieberInDenLetzten4TagenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "fieberInDenLetzten4Tagen", FieberInDenLetzten4TagenCluster.class, this);

  public SelectAqlField<SchuettelfrostInDenLetzten24StundenCluster> SCHUETTELFROST_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<SchuettelfrostInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "schuettelfrostInDenLetzten24Stunden", SchuettelfrostInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<HustenInDenLetzten24StundenCluster> HUSTEN_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<HustenInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "hustenInDenLetzten24Stunden", HustenInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<SchnupfenInDenLetzten24StundenCluster> SCHNUPFEN_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<SchnupfenInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "schnupfenInDenLetzten24Stunden", SchnupfenInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<HalsschmerzenInDenLetzten24StundenCluster> HALSSCHMERZEN_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<HalsschmerzenInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "halsschmerzenInDenLetzten24Stunden", HalsschmerzenInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<AtemproblemeCluster> ATEMPROBLEME = new AqlFieldImp<AtemproblemeCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "atemprobleme", AtemproblemeCluster.class, this);

  public SelectAqlField<SchlappheitAngeschlagenheitCluster> SCHLAPPHEIT_ANGESCHLAGENHEIT = new AqlFieldImp<SchlappheitAngeschlagenheitCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "schlappheitAngeschlagenheit", SchlappheitAngeschlagenheitCluster.class, this);

  public SelectAqlField<GliederschmerzenCluster> GLIEDERSCHMERZEN = new AqlFieldImp<GliederschmerzenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "gliederschmerzen", GliederschmerzenCluster.class, this);

  public SelectAqlField<DurchfallCluster> DURCHFALL = new AqlFieldImp<DurchfallCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "durchfall", DurchfallCluster.class, this);

  public SelectAqlField<KopfschmerzenCluster> KOPFSCHMERZEN = new AqlFieldImp<KopfschmerzenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "kopfschmerzen", KopfschmerzenCluster.class, this);

  public SelectAqlField<GeschmacksUndOderGeruchsverlustCluster> GESCHMACKS_UND_ODER_GERUCHSVERLUST = new AqlFieldImp<GeschmacksUndOderGeruchsverlustCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]", "geschmacksUndOderGeruchsverlust", GeschmacksUndOderGeruchsverlustCluster.class, this);

  public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ProblemDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ProblemDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProblemDiagnoseEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ProblemDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static ProblemDiagnoseEvaluationContainment getInstance() {
    return new ProblemDiagnoseEvaluationContainment();
  }
}
