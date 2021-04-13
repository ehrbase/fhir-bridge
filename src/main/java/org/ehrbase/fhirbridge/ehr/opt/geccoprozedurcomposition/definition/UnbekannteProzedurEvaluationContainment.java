package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class UnbekannteProzedurEvaluationContainment extends Containment {
  public SelectAqlField<UnbekannteProzedurEvaluation> UNBEKANNTE_PROZEDUR_EVALUATION = new AqlFieldImp<UnbekannteProzedurEvaluation>(UnbekannteProzedurEvaluation.class, "", "UnbekannteProzedurEvaluation", UnbekannteProzedurEvaluation.class, this);

  public SelectAqlField<NameDerProzedurDefiningCode> UNBEKANNTE_PROZEDUR_DEFINING_CODE = new AqlFieldImp<NameDerProzedurDefiningCode>(UnbekannteProzedurEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "unbekannteProzedurDefiningCode", NameDerProzedurDefiningCode.class, this);

  public SelectAqlField<NullFlavour> UNBEKANNTE_PROZEDUR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(UnbekannteProzedurEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "unbekannteProzedurNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> AUSSAGE_UEBER_DIE_FEHLENDE_INFORMATION_VALUE = new AqlFieldImp<String>(UnbekannteProzedurEvaluation.class, "/data[at0001]/items[at0005]/value|value", "aussageUeberDieFehlendeInformationValue", String.class, this);

  public SelectAqlField<NullFlavour> AUSSAGE_UEBER_DIE_FEHLENDE_INFORMATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(UnbekannteProzedurEvaluation.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "aussageUeberDieFehlendeInformationNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UnbekannteProzedurEvaluation.class, "/protocol[at0003]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(UnbekannteProzedurEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UnbekannteProzedurEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(UnbekannteProzedurEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private UnbekannteProzedurEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.absence.v2");
  }

  public static UnbekannteProzedurEvaluationContainment getInstance() {
    return new UnbekannteProzedurEvaluationContainment();
  }
}
