package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

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

public class UnbekannteReisehistorieEvaluationContainment extends Containment {
  public SelectAqlField<UnbekannteReisehistorieEvaluation> UNBEKANNTE_REISEHISTORIE_EVALUATION = new AqlFieldImp<UnbekannteReisehistorieEvaluation>(UnbekannteReisehistorieEvaluation.class, "", "UnbekannteReisehistorieEvaluation", UnbekannteReisehistorieEvaluation.class, this);

  public SelectAqlField<ProblemDiagnoseDefiningCode> FEHLENDE_INFORMATION_DEFINING_CODE = new AqlFieldImp<ProblemDiagnoseDefiningCode>(UnbekannteReisehistorieEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "fehlendeInformationDefiningCode", ProblemDiagnoseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> FEHLENDE_INFORMATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(UnbekannteReisehistorieEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "fehlendeInformationNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AussageUeberDieFehlendeInformationDefiningCode> AUSSAGE_UEBER_DIE_FEHLENDE_INFORMATION_DEFINING_CODE = new AqlFieldImp<AussageUeberDieFehlendeInformationDefiningCode>(UnbekannteReisehistorieEvaluation.class, "/data[at0001]/items[at0005]/value|defining_code", "aussageUeberDieFehlendeInformationDefiningCode", AussageUeberDieFehlendeInformationDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AUSSAGE_UEBER_DIE_FEHLENDE_INFORMATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(UnbekannteReisehistorieEvaluation.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "aussageUeberDieFehlendeInformationNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UnbekannteReisehistorieEvaluation.class, "/protocol[at0003]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(UnbekannteReisehistorieEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UnbekannteReisehistorieEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(UnbekannteReisehistorieEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private UnbekannteReisehistorieEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.absence.v2");
  }

  public static UnbekannteReisehistorieEvaluationContainment getInstance() {
    return new UnbekannteReisehistorieEvaluationContainment();
  }
}
