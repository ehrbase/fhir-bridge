package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;

public class AusgeschlosseneDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<AusgeschlosseneDiagnoseEvaluation> AUSGESCHLOSSENE_DIAGNOSE_EVALUATION = new AqlFieldImp<AusgeschlosseneDiagnoseEvaluation>(AusgeschlosseneDiagnoseEvaluation.class, "", "AusgeschlosseneDiagnoseEvaluation", AusgeschlosseneDiagnoseEvaluation.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AusgeschlosseneDiagnoseEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<ProblemDiagnoseDefiningcode> PROBLEM_DIAGNOSE_DEFININGCODE = new AqlFieldImp<ProblemDiagnoseDefiningcode>(AusgeschlosseneDiagnoseEvaluation.class, "/data[at0001]/items[at0003]/value|defining_code", "problemDiagnoseDefiningcode", ProblemDiagnoseDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AusgeschlosseneDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<AussageUberDenAusschlussDefiningcode> AUSSAGE_UBER_DEN_AUSSCHLUSS_DEFININGCODE = new AqlFieldImp<AussageUberDenAusschlussDefiningcode>(AusgeschlosseneDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "aussageUberDenAusschlussDefiningcode", AussageUberDenAusschlussDefiningcode.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AusgeschlosseneDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  private AusgeschlosseneDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static AusgeschlosseneDiagnoseEvaluationContainment getInstance() {
    return new AusgeschlosseneDiagnoseEvaluationContainment();
  }
}
