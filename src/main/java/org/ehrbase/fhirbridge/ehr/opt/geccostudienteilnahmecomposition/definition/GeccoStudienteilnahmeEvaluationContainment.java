package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class GeccoStudienteilnahmeEvaluationContainment extends Containment {
  public SelectAqlField<GeccoStudienteilnahmeEvaluation> GECCO_STUDIENTEILNAHME_EVALUATION = new AqlFieldImp<GeccoStudienteilnahmeEvaluation>(GeccoStudienteilnahmeEvaluation.class, "", "GeccoStudienteilnahmeEvaluation", GeccoStudienteilnahmeEvaluation.class, this);

  public SelectAqlField<BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode> BEREITS_AN_INTERVENTIONELLEN_KLINISCHEN_STUDIEN_TEILGENOMMEN_DEFINING_CODE = new AqlFieldImp<BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode>(GeccoStudienteilnahmeEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "bereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode", BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.class, this);

  public SelectAqlField<NullFlavour> BEREITS_AN_INTERVENTIONELLEN_KLINISCHEN_STUDIEN_TEILGENOMMEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeccoStudienteilnahmeEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "bereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<StudienteilnahmeCluster> STUDIENTEILNAHME = new AqlFieldImp<StudienteilnahmeCluster>(GeccoStudienteilnahmeEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.study_participation.v1]", "studienteilnahme", StudienteilnahmeCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(GeccoStudienteilnahmeEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GeccoStudienteilnahmeEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GeccoStudienteilnahmeEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private GeccoStudienteilnahmeEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.gecco_study_participation.v0");
  }

  public static GeccoStudienteilnahmeEvaluationContainment getInstance() {
    return new GeccoStudienteilnahmeEvaluationContainment();
  }
}
