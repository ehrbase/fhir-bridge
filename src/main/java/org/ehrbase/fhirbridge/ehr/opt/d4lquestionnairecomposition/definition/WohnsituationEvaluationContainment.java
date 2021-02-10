package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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

public class WohnsituationEvaluationContainment extends Containment {
  public SelectAqlField<WohnsituationEvaluation> WOHNSITUATION_EVALUATION = new AqlFieldImp<WohnsituationEvaluation>(WohnsituationEvaluation.class, "", "WohnsituationEvaluation", WohnsituationEvaluation.class, this);

  public SelectAqlField<WohnsituationDefiningCode> WOHNSITUATION_DEFINING_CODE = new AqlFieldImp<WohnsituationDefiningCode>(WohnsituationEvaluation.class, "/data[at0001]/items[at0004]/value|defining_code", "wohnsituationDefiningCode", WohnsituationDefiningCode.class, this);

  public SelectAqlField<NullFlavour> WOHNSITUATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(WohnsituationEvaluation.class, "/data[at0001]/items[at0004]/null_flavour|defining_code", "wohnsituationNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERGAENZENDE_DETAILS = new ListAqlFieldImp<Cluster>(WohnsituationEvaluation.class, "/data[at0001]/items[at0008]", "ergaenzendeDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(WohnsituationEvaluation.class, "/protocol[at0002]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(WohnsituationEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(WohnsituationEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(WohnsituationEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private WohnsituationEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.living_arrangement.v0");
  }

  public static WohnsituationEvaluationContainment getInstance() {
    return new WohnsituationEvaluationContainment();
  }
}
