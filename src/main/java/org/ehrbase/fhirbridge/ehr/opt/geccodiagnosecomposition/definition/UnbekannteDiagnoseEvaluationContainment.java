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

public class UnbekannteDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<UnbekannteDiagnoseEvaluation> UNBEKANNTE_DIAGNOSE_EVALUATION = new AqlFieldImp<UnbekannteDiagnoseEvaluation>(UnbekannteDiagnoseEvaluation.class, "", "UnbekannteDiagnoseEvaluation", UnbekannteDiagnoseEvaluation.class, this);

  public SelectAqlField<NameDesProblemsDerDiagnoseDefiningCode> UNBEKANNTE_DIAGNOSE_DEFINING_CODE = new AqlFieldImp<NameDesProblemsDerDiagnoseDefiningCode>(UnbekannteDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "unbekannteDiagnoseDefiningCode", NameDesProblemsDerDiagnoseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> UNBEKANNTE_DIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(UnbekannteDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "unbekannteDiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AussageUeberDieFehlendeInformationDefiningCode> AUSSAGE_UEBER_DIE_FEHLENDE_INFORMATION_DEFINING_CODE = new AqlFieldImp<AussageUeberDieFehlendeInformationDefiningCode>(UnbekannteDiagnoseEvaluation.class, "/data[at0001]/items[at0005]/value|defining_code", "aussageUeberDieFehlendeInformationDefiningCode", AussageUeberDieFehlendeInformationDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AUSSAGE_UEBER_DIE_FEHLENDE_INFORMATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(UnbekannteDiagnoseEvaluation.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "aussageUeberDieFehlendeInformationNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UnbekannteDiagnoseEvaluation.class, "/protocol[at0003]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(UnbekannteDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UnbekannteDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(UnbekannteDiagnoseEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private UnbekannteDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.absence.v2");
  }

  public static UnbekannteDiagnoseEvaluationContainment getInstance() {
    return new UnbekannteDiagnoseEvaluationContainment();
  }
}
