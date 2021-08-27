package org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ExclusionSpecificEvaluationContainment extends Containment {
  public SelectAqlField<ExclusionSpecificEvaluation> EXCLUSION_SPECIFIC_EVALUATION = new AqlFieldImp<ExclusionSpecificEvaluation>(ExclusionSpecificEvaluation.class, "", "ExclusionSpecificEvaluation", ExclusionSpecificEvaluation.class, this);

  public SelectAqlField<ExclusionStatementDefiningCode> EXCLUSION_STATEMENT_DEFINING_CODE = new AqlFieldImp<ExclusionStatementDefiningCode>(ExclusionSpecificEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "exclusionStatementDefiningCode", ExclusionStatementDefiningCode.class, this);

  public SelectAqlField<NullFlavour> EXCLUSION_STATEMENT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ExclusionSpecificEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "exclusionStatementNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<RauchverhaltenDefiningCode> PROBLEM_DIAGNOSIS_DEFINING_CODE = new AqlFieldImp<RauchverhaltenDefiningCode>(ExclusionSpecificEvaluation.class, "/data[at0001]/items[at0003]/value|defining_code", "problemDiagnosisDefiningCode", RauchverhaltenDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PROBLEM_DIAGNOSIS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ExclusionSpecificEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "problemDiagnosisNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(ExclusionSpecificEvaluation.class, "/protocol[at0009]/items[at0011]", "extension", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ExclusionSpecificEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ExclusionSpecificEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ExclusionSpecificEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ExclusionSpecificEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static ExclusionSpecificEvaluationContainment getInstance() {
    return new ExclusionSpecificEvaluationContainment();
  }
}
