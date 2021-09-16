package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

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

public class AusgeschlosseneDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<AusgeschlosseneDiagnoseEvaluation> AUSGESCHLOSSENE_DIAGNOSE_EVALUATION = new AqlFieldImp<AusgeschlosseneDiagnoseEvaluation>(AusgeschlosseneDiagnoseEvaluation.class, "", "AusgeschlosseneDiagnoseEvaluation", AusgeschlosseneDiagnoseEvaluation.class, this);

  public SelectAqlField<AussageUeberDenAusschlussDefiningCode> AUSSAGE_UEBER_DEN_AUSSCHLUSS_DEFINING_CODE = new AqlFieldImp<AussageUeberDenAusschlussDefiningCode>(AusgeschlosseneDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "aussageUeberDenAusschlussDefiningCode", AussageUeberDenAusschlussDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AUSSAGE_UEBER_DEN_AUSSCHLUSS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AusgeschlosseneDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "aussageUeberDenAusschlussNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NameDesProblemsDerDiagnoseDefiningCode> PROBLEM_DIAGNOSE_DEFINING_CODE = new AqlFieldImp<NameDesProblemsDerDiagnoseDefiningCode>(AusgeschlosseneDiagnoseEvaluation.class, "/data[at0001]/items[at0003]/value|defining_code", "problemDiagnoseDefiningCode", NameDesProblemsDerDiagnoseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PROBLEM_DIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AusgeschlosseneDiagnoseEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "problemDiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AusgeschlosseneDiagnoseEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AusgeschlosseneDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AusgeschlosseneDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AusgeschlosseneDiagnoseEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AusgeschlosseneDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static AusgeschlosseneDiagnoseEvaluationContainment getInstance() {
    return new AusgeschlosseneDiagnoseEvaluationContainment();
  }
}
