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

public class ZusammenfassungDerBeschaeftigungEvaluationContainment extends Containment {
  public SelectAqlField<ZusammenfassungDerBeschaeftigungEvaluation> ZUSAMMENFASSUNG_DER_BESCHAEFTIGUNG_EVALUATION = new AqlFieldImp<ZusammenfassungDerBeschaeftigungEvaluation>(ZusammenfassungDerBeschaeftigungEvaluation.class, "", "ZusammenfassungDerBeschaeftigungEvaluation", ZusammenfassungDerBeschaeftigungEvaluation.class, this);

  public ListSelectAqlField<Cluster> BESCHAEFTIGUNGSEPISODE = new ListAqlFieldImp<Cluster>(ZusammenfassungDerBeschaeftigungEvaluation.class, "/data[at0001]/items[at0003]", "beschaeftigungsepisode", Cluster.class, this);

  public SelectAqlField<BeschaeftigungCluster> BESCHAEFTIGUNG = new AqlFieldImp<BeschaeftigungCluster>(ZusammenfassungDerBeschaeftigungEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]", "beschaeftigung", BeschaeftigungCluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ZusammenfassungDerBeschaeftigungEvaluation.class, "/protocol[at0007]/items[at0008]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ZusammenfassungDerBeschaeftigungEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ZusammenfassungDerBeschaeftigungEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ZusammenfassungDerBeschaeftigungEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ZusammenfassungDerBeschaeftigungEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.occupation_summary.v1");
  }

  public static ZusammenfassungDerBeschaeftigungEvaluationContainment getInstance() {
    return new ZusammenfassungDerBeschaeftigungEvaluationContainment();
  }
}
