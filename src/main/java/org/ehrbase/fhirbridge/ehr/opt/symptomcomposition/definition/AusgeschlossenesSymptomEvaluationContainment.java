package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

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

public class AusgeschlossenesSymptomEvaluationContainment extends Containment {
  public SelectAqlField<AusgeschlossenesSymptomEvaluation> AUSGESCHLOSSENES_SYMPTOM_EVALUATION = new AqlFieldImp<AusgeschlossenesSymptomEvaluation>(AusgeschlossenesSymptomEvaluation.class, "", "AusgeschlossenesSymptomEvaluation", AusgeschlossenesSymptomEvaluation.class, this);

  public SelectAqlField<AussageUeberDenAusschlussDefiningCode> AUSSAGE_UEBER_DEN_AUSSCHLUSS_DEFINING_CODE = new AqlFieldImp<AussageUeberDenAusschlussDefiningCode>(AusgeschlossenesSymptomEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "aussageUeberDenAusschlussDefiningCode", AussageUeberDenAusschlussDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AUSSAGE_UEBER_DEN_AUSSCHLUSS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AusgeschlossenesSymptomEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "aussageUeberDenAusschlussNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NameDesSymptomsKrankheitsanzeichensDefiningCode> PROBLEM_DIAGNOSE_DEFINING_CODE = new AqlFieldImp<NameDesSymptomsKrankheitsanzeichensDefiningCode>(AusgeschlossenesSymptomEvaluation.class, "/data[at0001]/items[at0003]/value|defining_code", "problemDiagnoseDefiningCode", NameDesSymptomsKrankheitsanzeichensDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PROBLEM_DIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AusgeschlossenesSymptomEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "problemDiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AusgeschlossenesSymptomEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AusgeschlossenesSymptomEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AusgeschlossenesSymptomEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AusgeschlossenesSymptomEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AusgeschlossenesSymptomEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static AusgeschlossenesSymptomEvaluationContainment getInstance() {
    return new AusgeschlossenesSymptomEvaluationContainment();
  }
}
