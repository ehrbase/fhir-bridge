package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class ProblemDiagnoseAttributClusterContainment extends Containment {
  public SelectAqlField<ProblemDiagnoseAttributCluster> PROBLEM_DIAGNOSE_ATTRIBUT_CLUSTER = new AqlFieldImp<ProblemDiagnoseAttributCluster>(ProblemDiagnoseAttributCluster.class, "", "ProblemDiagnoseAttributCluster", ProblemDiagnoseAttributCluster.class, this);

  public ListSelectAqlField<ProblemDiagnoseAttributDiagnoserolleElement> DIAGNOSEROLLE = new ListAqlFieldImp<ProblemDiagnoseAttributDiagnoserolleElement>(ProblemDiagnoseAttributCluster.class, "/items[at0063]", "diagnoserolle", ProblemDiagnoseAttributDiagnoserolleElement.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProblemDiagnoseAttributCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ProblemDiagnoseAttributClusterContainment() {
    super("openEHR-EHR-CLUSTER.problem_qualifier.v2");
  }

  public static ProblemDiagnoseAttributClusterContainment getInstance() {
    return new ProblemDiagnoseAttributClusterContainment();
  }
}
